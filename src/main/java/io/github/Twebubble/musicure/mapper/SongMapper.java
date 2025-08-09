package io.github.Twebubble.musicure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.Twebubble.musicure.model.domain.Song;
import io.github.Twebubble.musicure.service.impl.SongServiceImpl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SongMapper extends BaseMapper<Song> {
    int deleteByPrimaryKey(Integer id);

    int insert(Song row);

    int insertSelective(Song row);

    List<Song> selectByExampleWithBLOBs(SongServiceImpl example);

    List<Song> selectByExample(SongServiceImpl example);

    Song selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Song row);

    int updateByPrimaryKeyWithBLOBs(Song row);

    int updateByPrimaryKey(Song row);
}