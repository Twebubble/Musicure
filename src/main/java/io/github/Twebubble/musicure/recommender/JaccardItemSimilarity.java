package io.github.Twebubble.musicure.recommender;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class JaccardItemSimilarity implements ItemSimilarity {

    private final DataModel dataModel;

    // 构造器注入DataModel
    public JaccardItemSimilarity(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    // 核心方法：计算两首歌曲（item1, item2）的Jaccard相似度
    @Override
    public double itemSimilarity(long songId1, long songId2) throws TasteException {
        // 分别获取对songId1、songId2有交互的用户集合（偏好值>0）
        Set<Long> usersForSong1 = getInteractedUsers(songId1);
        Set<Long> usersForSong2 = getInteractedUsers(songId2);

        // 计算Jaccard相似度：交集大小 / 并集大小
        int intersectionSize = calculateIntersectionSize(usersForSong1, usersForSong2);
        int unionSize = calculateUnionSize(usersForSong1, usersForSong2);

        // 避免除以0（无用户交互的歌曲，相似度为0）
        return unionSize == 0 ? 0.0 : (double) intersectionSize / unionSize;
    }

    @Override
    public double[] itemSimilarities(long itemId, long[] itemIds) throws TasteException {
        //计算一个物品与多个物品的相似度，有需要再实现
        return new double[0];
    }

    @Override
    public long[] allSimilarItemIDs(long l) throws TasteException {
        //返回与指定物品（如歌曲）存在相似性的所有物品的 ID 列表
        return new long[0];
    }

    // 辅助方法1：获取对某歌曲有交互的用户ID集合（偏好值>0）
    private Set<Long> getInteractedUsers(long songId) throws TasteException {
        Set<Long> userIds = new HashSet<>();

        // 从DataModel中获取该歌曲的所有用户偏好
        PreferenceArray prefsForSong = dataModel.getPreferencesForItem(songId);
        for (int i = 0; i < prefsForSong.length(); i++) {
            float preferenceValue = prefsForSong.getValue(i); // Mahout 0.13.0 使用 float
            // 仅保留偏好值>0的用户（有收藏/评论行为）
            if (preferenceValue > 0) {
                userIds.add(prefsForSong.getUserID(i));
            }
        }
        return userIds;
    }

    // 辅助方法2：计算两个用户集合的交集大小
    private int calculateIntersectionSize(Set<Long> set1, Set<Long> set2) {
        Set<Long> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        return intersection.size();
    }

    // 辅助方法3：计算两个用户集合的并集大小
    private int calculateUnionSize(Set<Long> set1, Set<Long> set2) {
        Set<Long> union = new HashSet<>(set1);
        union.addAll(set2);
        return union.size();
    }

    @Override
    public void refresh(Collection<Refreshable> collection) {
        // 如果需要，可以在这里实现数据刷新逻辑
    }

    // 实现Mahout要求的其他接口方法
//    @Override
//    public double itemSimilarity(long songId1, long songId2, long userId) throws TasteException {
//        // 单用户视角的相似度（暂用全局相似度，可后续优化）
//        return itemSimilarity(songId1, songId2);
//    }

}
