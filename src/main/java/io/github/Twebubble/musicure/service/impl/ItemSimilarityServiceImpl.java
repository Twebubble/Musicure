package io.github.Twebubble.musicure.service.impl;

import io.github.Twebubble.musicure.recommender.JaccardItemSimilarity;
import io.github.Twebubble.musicure.service.ItemSimilarityService;
import io.github.Twebubble.musicure.service.MahoutDataModelService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemSimilarityServiceImpl implements ItemSimilarityService {

    @Autowired
    private MahoutDataModelService dataModelService;

    // 计算两首歌曲的行为相似度（基于收藏/评论）
    public double calculateSongBehaviorSimilarity(Long songId1, Long songId2) {
        try {
            // 构建DataModel
            DataModel dataModel = dataModelService.buildBehaviorDataModel();

            // 初始化Jaccard相似度计算器
            JaccardItemSimilarity jaccardSimilarity = new JaccardItemSimilarity(dataModel);

            // 计算并返回相似度（0~1之间，值越大越相似）
            return jaccardSimilarity.itemSimilarity(songId1, songId2);
        } catch (TasteException e) {
            // 异常处理（如歌曲不存在、无交互数据）
            // 使用您项目的日志框架记录错误
            // 例如: log.error("计算歌曲行为相似度失败：songId1={}, songId2={}", songId1, songId2, e);
            return 0.0;
        }
    }
}
