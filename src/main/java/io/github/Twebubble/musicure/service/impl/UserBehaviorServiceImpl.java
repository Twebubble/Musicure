package io.github.Twebubble.musicure.service.impl;

import io.github.Twebubble.musicure.mapper.UserBehaviorMapper;
import io.github.Twebubble.musicure.model.domain.UserSongBehavior;
import io.github.Twebubble.musicure.model.domain.UserSongPreference;
import io.github.Twebubble.musicure.service.UserBehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserBehaviorServiceImpl implements UserBehaviorService {

    @Autowired
    private UserBehaviorMapper userBehaviorMapper;

    // 将原始行为转化为量化的用户-歌曲偏好
    public List<UserSongPreference> getQuantifiedPreferences() {
        // 1. 从数据库查询所有用户-歌曲的有效行为
        List<UserSongBehavior> rawBehaviors = userBehaviorMapper.listUserSongBehaviors();
        // 2. 按「用户ID+歌曲ID」分组，计算同一用户对同一歌曲的总偏好值
        Map<String, Double> userSongPrefMap = new HashMap<>();
        for (UserSongBehavior behavior : rawBehaviors) {
            // 构建分组key：userId_songId（避免同一用户对同一歌曲的重复计算）
            String key = behavior.getUserId() + "_" + behavior.getSongId();
            // 根据行为类型获取基础权重
            double weight = getBehaviorWeight(behavior.getBehaviorType());
            // 叠加偏好值（若同一用户对同一歌曲有多种行为，权重相加）
            userSongPrefMap.put(key, userSongPrefMap.getOrDefault(key, 0.0) + weight);
        }
        // 3. 转化为List<UserSongPreference>（用于后续构建DataModel）
        return userSongPrefMap.entrySet().stream()
                .map(entry -> {
                    String[] keyParts = entry.getKey().split("_");
                    UserSongPreference preference = new UserSongPreference();
                    preference.setUserId(Long.parseLong(keyParts[0]));
                    preference.setSongId(Long.parseLong(keyParts[1]));
                    preference.setPreferenceValue(entry.getValue());
                    return preference;
                })
                .collect(Collectors.toList());
    }

    // 行为权重映射,  TODO:后续可更改映射权重
    private double getBehaviorWeight(Integer behaviorType) {
        return switch (behaviorType) {
            case 1 -> 1.0; // 评论行为
            case 2 -> 2.0; // 收藏行为
            default -> 0.0; // 无效行为（过滤）
        };
    }
}
