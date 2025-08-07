package io.github.Twebubble.musicure.mapper;

import io.github.Twebubble.musicure.model.domain.RankList;
import io.github.Twebubble.musicure.service.impl.RankListImpl;
import java.util.List;

public interface RankListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RankList row);

    int insertSelective(RankList row);

    List<RankList> selectByExample(RankListImpl example);

    RankList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RankList row);

    int updateByPrimaryKey(RankList row);
}