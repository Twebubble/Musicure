package io.github.Twebubble.musicure.service;

import io.github.Twebubble.musicure.common.R;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.Twebubble.musicure.model.domain.Emotion;

public interface EmotionService extends IService<Emotion> {
    R emotionOfUser(Integer userId);

    Emotion getLatestEmotionByUserId(Long userId);

//    R latestEmotionOfUser(Integer userId);

}
