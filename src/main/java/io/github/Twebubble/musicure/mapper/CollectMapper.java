package io.github.Twebubble.musicure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.Twebubble.musicure.model.domain.Collect;
import io.github.Twebubble.musicure.service.impl.CollectServiceImpl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CollectMapper extends BaseMapper<Collect> {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect row);

    int insertSelective(Collect row);

    List<Collect> selectByExample(CollectServiceImpl example);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect row);

    int updateByPrimaryKey(Collect row);
}