package io.github.Twebubble.musicure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.Twebubble.musicure.model.domain.EEGData;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface EEGDataMapper extends BaseMapper<EEGData> {
    List<String> selectDistinctChannels(Integer userId);

//    LocalDateTime selectLatestTimestamp(Integer userId);
    LocalTime selectLatestTimestamp(Integer userId);

//    List<EEGData> selectByUserAndChannelAndTimeRange(Integer userId, String channel, LocalDateTime startTime, LocalDateTime latestTimestamp);
    List<EEGData> selectByUserAndChannelAndTimeRange(Integer userId, String channel, LocalTime startTime, LocalTime latestTimestamp);

    //    int deleteByPrimaryKey(Integer id);
//
//    int insert(EEGData row);
//
//    int insertSelective(EEGData row);
//
//    List<EEGData> selectByExample(EEGDataServiceImpl example);
//
//    EEGData selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(EEGData row);
//
//    int updateByPrimaryKey(EEGData row);
}