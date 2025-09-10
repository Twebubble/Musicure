package io.github.Twebubble.musicure.service.impl;

import io.github.Twebubble.musicure.model.domain.UserSongPreference;
import io.github.Twebubble.musicure.service.MahoutDataModelService;
import io.github.Twebubble.musicure.service.UserBehaviorService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.*;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MahoutDataModelServiceImpl implements MahoutDataModelService {

    @Autowired
    private UserBehaviorService userBehaviorService;

    // 构建用于计算行为相似度的DataModel
    public DataModel buildBehaviorDataModel() throws TasteException {
        // 1. 获取量化后的用户-歌曲偏好列表
        List<UserSongPreference> preferences = userBehaviorService.getQuantifiedPreferences();
        // 2. 按用户分组，构建UserPreferenceArray（Mahout要求的格式）
        Map<Long, List<Preference>> userPrefMap = new HashMap<>();
        for (UserSongPreference pref : preferences) {
            // 创建Mahout的Preference对象（用户ID、歌曲ID、偏好值）
            Preference mahoutPref = new GenericPreference(
                    pref.getUserId(),
                    pref.getSongId(),
                    pref.getPreferenceValue()
            );
            // 按用户ID分组存储
//            userPrefMap.computeIfAbsent(pref.getUserId(), k -> new ArrayList<>())
//                    .add(mahoutPref);
            Long userId = pref.getUserId();
            if (!userPrefMap.containsKey(userId)) {
                userPrefMap.put(userId, new ArrayList<Preference>());
            }
            userPrefMap.get(userId).add(mahoutPref);
        }
        // 3. 转化为DataModel（GenericDataModel是Mahout的默认实现）
//        List<UserPreferenceArray> userPrefArrays = new ArrayList<>();
        FastByIDMap<PreferenceArray> userData = new FastByIDMap<PreferenceArray>();
        for (Map.Entry<Long, List<Preference>> entry : userPrefMap.entrySet()) {
            // 将用户的所有偏好转化为UserPreferenceArray
//            UserPreferenceArray array = new GenericUserPreferenceArray(entry.getValue());
//            array.setUserID(0, entry.getKey()); // 绑定用户ID
//            userPrefArrays.add(array);
            Long userId = entry.getKey();
            List<Preference> prefs = entry.getValue();

            // 创建 GenericUserPreferenceArray
            PreferenceArray prefArray = new GenericUserPreferenceArray(prefs.size());
            prefArray.setUserID(0, userId);

            // 填充偏好数据
            for (int i = 0; i < prefs.size(); i++) {
                Preference pref = prefs.get(i);
                prefArray.setItemID(i, pref.getItemID());
                prefArray.setValue(i, pref.getValue());
            }

            // 添加到用户数据映射
            userData.put(userId, prefArray);
        }
        // 4. 返回构建好的DataModel
        return new GenericDataModel(userData);
    }
}
