package io.github.Twebubble.musicure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.Twebubble.musicure.model.domain.ListSong;
import io.github.Twebubble.musicure.service.impl.ListSongServiceImpl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ListSongMapper extends BaseMapper<ListSong> {
    int deleteByPrimaryKey(Integer id);

    int insert(ListSong row);

    int insertSelective(ListSong row);

    List<ListSong> selectByExample(ListSongServiceImpl example);

    ListSong selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ListSong row);

    int updateByPrimaryKey(ListSong row);
}