package io.github.Twebubble.musicure.mapper;

import io.github.Twebubble.musicure.model.domain.ListSong;
import io.github.Twebubble.musicure.service.impl.ListSongImpl;
import java.util.List;

public interface ListSongMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ListSong row);

    int insertSelective(ListSong row);

    List<ListSong> selectByExample(ListSongImpl example);

    ListSong selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ListSong row);

    int updateByPrimaryKey(ListSong row);
}