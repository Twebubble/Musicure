package io.github.Twebubble.musicure.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

@TableName(value = "emotion")
@Data
public class Emotion implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    @TableField(fill = FieldFill.INSERT)
    private Date recoTime;

    private Byte emotion;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}