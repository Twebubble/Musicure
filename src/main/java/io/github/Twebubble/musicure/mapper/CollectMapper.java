package io.github.Twebubble.musicure.mapper;

import io.github.Twebubble.musicure.model.domain.Collect;
import io.github.Twebubble.musicure.service.impl.CollectImpl;
import java.util.List;

public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect row);

    int insertSelective(Collect row);

    List<Collect> selectByExample(CollectImpl example);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect row);

    int updateByPrimaryKey(Collect row);
}