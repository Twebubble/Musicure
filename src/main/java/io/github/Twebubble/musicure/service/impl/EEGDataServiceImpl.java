package io.github.Twebubble.musicure.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.Twebubble.musicure.common.R;
import io.github.Twebubble.musicure.mapper.EEGDataMapper;
import io.github.Twebubble.musicure.model.domain.EEGData;
import io.github.Twebubble.musicure.service.EEGDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EEGDataServiceImpl extends ServiceImpl<EEGDataMapper, EEGData> implements EEGDataService {

    @Autowired
    private EEGDataMapper eegDataMapper;

    @Value("${minio.bucket-name}")
    String bucketName;

    /**
     * 根据用户ID获取脑电数据
     * @param userId 用户ID
     * @param dataPoints 数据点数，默认300
     * @return 统一响应对象
     */
    @Override
    public R getEEGDataByUserId(Integer userId, Integer dataPoints) {
        try {
            // 参数校验
            if (userId == null || userId <= 0) {
                return R.error("用户ID不能为空且必须大于0");
            }

            if (dataPoints == null) {
                dataPoints = 300; // 默认获取300个数据点
            } else if (dataPoints <= 0) {
                return R.error("数据点数必须大于0");
            }

            // 获取用户的所有通道
            List<String> channels = eegDataMapper.selectDistinctChannels(userId);
            if (channels == null || channels.isEmpty()) {
                return R.warning("用户" + userId + "没有脑电数据");
            }

            // 获取最新时间戳
//            LocalDateTime latestTimestamp = eegDataMapper.selectLatestTimestamp(userId);
            LocalTime latestTimestamp = eegDataMapper.selectLatestTimestamp(userId);
            if (latestTimestamp == null) {
                return R.warning("用户" + userId + "没有脑电数据记录");
            }

            // 计算开始时间
//            LocalDateTime startTime = latestTimestamp.minusSeconds(dataPoints - 1);
            LocalTime startTime = latestTimestamp.minusSeconds(dataPoints - 1);
            // 获取每个通道的数据
            Map<String, List<Double>> channelDataMap = new HashMap<>();
            for (String channel : channels) {
                List<EEGData> eegDataList = eegDataMapper.selectByUserAndChannelAndTimeRange(
                        userId, channel, startTime, latestTimestamp);

                // 按时间戳排序并提取值
                List<Double> values = eegDataList.stream()
                        .sorted(Comparator.comparing(EEGData::getTimestamp))
                        .map(EEGData::getValue)
                        .collect(Collectors.toList());

                channelDataMap.put(channel, values);
            }

            // 生成时间数据
            List<String> timeData = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            for (int i = 0; i < dataPoints; i++) {
//                LocalDateTime time = startTime.plusSeconds(i);
                LocalTime time = startTime.plusSeconds(i);
                timeData.add(time.format(formatter));
            }

            // 转换为前端需要的格式
            List<Map<String, Object>> formattedChannelData = new ArrayList<>();
            for (Map.Entry<String, List<Double>> entry : channelDataMap.entrySet()) {
                Map<String, Object> channelData = new HashMap<>();
                channelData.put("name", entry.getKey());
                channelData.put("data", entry.getValue());
                formattedChannelData.add(channelData);
            }

            // 构建返回结果
            Map<String, Object> result = new HashMap<>();
            result.put("timeData", timeData);
            result.put("channelData", formattedChannelData);

            return R.success("获取脑电数据成功", result);

        } catch (Exception e) {
           // log.error("获取用户{}脑电数据失败: {}", userId, e.getMessage(), e);
            return R.fatal("系统错误，获取脑电数据失败: " + e.getMessage());
        }
    }
}