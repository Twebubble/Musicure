package io.github.Twebubble.musicure.mapper;

import io.github.Twebubble.musicure.model.domain.Song;
import io.github.Twebubble.musicure.service.impl.SongImpl;
import java.util.List;

public interface SongMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Song row);

    int insertSelective(Song row);

    List<Song> selectByExampleWithBLOBs(SongImpl example);

    List<Song> selectByExample(SongImpl example);

    Song selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Song row);

    int updateByPrimaryKeyWithBLOBs(Song row);

    int updateByPrimaryKey(Song row);
}