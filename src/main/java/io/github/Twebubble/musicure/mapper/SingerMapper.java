package io.github.Twebubble.musicure.mapper;

import io.github.Twebubble.musicure.model.domain.Singer;
import io.github.Twebubble.musicure.service.impl.SingerImpl;
import java.util.List;

public interface SingerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Singer row);

    int insertSelective(Singer row);

    List<Singer> selectByExample(SingerImpl example);

    Singer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Singer row);

    int updateByPrimaryKey(Singer row);
}