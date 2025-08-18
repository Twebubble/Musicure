package io.github.Twebubble.musicure.model.request;

import lombok.Data;

//歌曲列表的详细信息/介绍

@Data
public class SongListRequest {
    private Integer id;

    private String title;

    private String pic;

    private String style;

    private String introduction;
}