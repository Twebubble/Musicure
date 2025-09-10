package io.github.Twebubble.musicure.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.Twebubble.musicure.common.R;
import io.github.Twebubble.musicure.mapper.EmotionMapper;
import io.github.Twebubble.musicure.model.domain.Emotion;
import io.github.Twebubble.musicure.service.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmotionServiceImpl extends ServiceImpl<EmotionMapper, Emotion> implements EmotionService {

    @Autowired
    private EmotionMapper emotionMapper;

    @Override
    public R emotionOfUser(Integer userId) {
        QueryWrapper<Emotion> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return R.success("用户情绪", emotionMapper.selectList(queryWrapper));
    }

    @Override
    public Emotion getLatestEmotionByUserId(Long userId) {
        return emotionMapper.selectLatestEmotionByUserId(userId);
    }

//    @Override
//    public R latestEmotionOfUser(Integer userId){
//        QueryWrapper<Emotion> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_id", userId)
//                .orderByDesc("reco_time")
//                .last("LIMIT 1");
//        return emotionMapper.selectOne(queryWrapper);
//    }
}