package io.github.Twebubble.musicure.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class UserSongBehavior {

    @TableField("user_id")
    private Long userId;       // 用户ID（对应表中user_id）

    @TableField("song_id")
    private Long songId;       // 歌曲ID（对应表中song_id）

    @TableField("behavior_type")
    private Integer behaviorType; // 行为类型：1=评论，2=收藏

    private Date firstInteractTime; // 首次交互时间（可选，用于后续时间衰减优化）
}
