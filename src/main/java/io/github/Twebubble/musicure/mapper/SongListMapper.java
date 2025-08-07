package io.github.Twebubble.musicure.mapper;

import io.github.Twebubble.musicure.model.domain.SongList;
import io.github.Twebubble.musicure.service.impl.SongListImpl;
import java.util.List;

public interface SongListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SongList row);

    int insertSelective(SongList row);

    List<SongList> selectByExampleWithBLOBs(SongListImpl example);

    List<SongList> selectByExample(SongListImpl example);

    SongList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SongList row);

    int updateByPrimaryKeyWithBLOBs(SongList row);

    int updateByPrimaryKey(SongList row);
}