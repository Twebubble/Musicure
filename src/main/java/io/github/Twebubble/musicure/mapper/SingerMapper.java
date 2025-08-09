package io.github.Twebubble.musicure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.Twebubble.musicure.model.domain.Singer;
import io.github.Twebubble.musicure.service.impl.SingerServiceImpl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SingerMapper extends BaseMapper<Singer> {
    int deleteByPrimaryKey(Integer id);

    int insert(Singer row);

    int insertSelective(Singer row);

    List<Singer> selectByExample(SingerServiceImpl example);

    Singer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Singer row);

    int updateByPrimaryKey(Singer row);
}