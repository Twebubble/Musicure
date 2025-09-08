package io.github.Twebubble.musicure.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

@TableName(value = "eeg_data")
@Data
public class EEGData {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String channel;

    private Date timestamp;

    private Double value;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}