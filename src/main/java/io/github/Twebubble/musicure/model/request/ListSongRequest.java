package io.github.Twebubble.musicure.model.request;

import lombok.Data;

// 一个列表里有哪几首歌
@Data
public class ListSongRequest {
    private Integer id;

    private Integer songId;

    private Integer songListId;
}
