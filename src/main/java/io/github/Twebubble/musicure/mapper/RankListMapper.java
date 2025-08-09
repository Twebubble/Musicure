package io.github.Twebubble.musicure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.Twebubble.musicure.model.domain.RankList;
import io.github.Twebubble.musicure.service.impl.RankListServiceImpl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RankListMapper extends BaseMapper<RankList> {
    int deleteByPrimaryKey(Long id);

    int insert(RankList row);

    int insertSelective(RankList row);

    List<RankList> selectByExample(RankListServiceImpl example);

    RankList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RankList row);

    int updateByPrimaryKey(RankList row);
}