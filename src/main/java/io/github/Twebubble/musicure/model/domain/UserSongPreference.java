package io.github.Twebubble.musicure.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class UserSongPreference {

    @TableField("user_id")
    private Long userId;       // 用户ID

    @TableField("song_id")
    private Long songId;       // 歌曲ID

    private float preferenceValue; // 量化的偏好值

    // 添加一个接受 Double 参数的 setter 方法
    public void setPreferenceValue(Double value) {
        this.preferenceValue = value != null ? value.floatValue() : 0.0f;
    }

}
