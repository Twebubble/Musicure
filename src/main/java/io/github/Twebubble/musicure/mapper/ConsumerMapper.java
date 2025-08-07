package io.github.Twebubble.musicure.mapper;

import io.github.Twebubble.musicure.model.domain.Consumer;
import io.github.Twebubble.musicure.service.impl.ConsumerImpl;
import java.util.List;

public interface ConsumerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Consumer row);

    int insertSelective(Consumer row);

    List<Consumer> selectByExample(ConsumerImpl example);

    Consumer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Consumer row);

    int updateByPrimaryKey(Consumer row);
}