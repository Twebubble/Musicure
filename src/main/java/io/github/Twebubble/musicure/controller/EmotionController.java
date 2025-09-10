package io.github.Twebubble.musicure.controller;

import io.github.Twebubble.musicure.common.R;
import io.github.Twebubble.musicure.model.domain.Emotion;
import io.github.Twebubble.musicure.service.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmotionController {

    @Autowired
    private EmotionService emotionService;

    /**
     * 获取用户最新情绪状态
     * @param userId 用户ID
     * @return 包含最新情绪状态的R对象
     */
    @GetMapping("emotion/latest/{userId}")
    public R getLatestEmotion(@PathVariable Long userId) {
        Emotion emotion = emotionService.getLatestEmotionByUserId(userId);
        if (emotion != null) {
            return R.success("成功获取用户最新情绪", emotion);
        } else {
            return R.error("未找到该用户的情绪记录");
        }
    }
}
