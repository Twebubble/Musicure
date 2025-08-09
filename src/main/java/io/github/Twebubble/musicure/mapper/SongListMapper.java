package io.github.Twebubble.musicure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.Twebubble.musicure.model.domain.SongList;
import io.github.Twebubble.musicure.service.impl.SongListServiceImpl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SongListMapper extends BaseMapper<SongList> {
    int deleteByPrimaryKey(Integer id);

    int insert(SongList row);

    int insertSelective(SongList row);

    List<SongList> selectByExampleWithBLOBs(SongListServiceImpl example);

    List<SongList> selectByExample(SongListServiceImpl example);

    SongList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SongList row);

    int updateByPrimaryKeyWithBLOBs(SongList row);

    int updateByPrimaryKey(SongList row);
}