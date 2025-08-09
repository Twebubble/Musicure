package io.github.Twebubble.musicure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.Twebubble.musicure.model.domain.Consumer;
import io.github.Twebubble.musicure.service.impl.ConsumerServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.checkerframework.checker.units.qual.C;

import java.util.List;
@Mapper
public interface ConsumerMapper extends BaseMapper<Consumer> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(Consumer row);
    //zaimapper.xml中这个对应的代码改成addUser了
//    int insertSelective(Consumer row);

//    List<Consumer> selectByExample(ConsumerServiceImpl example);

//    Consumer selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Consumer row);
//
//    int updateByPrimaryKey(Consumer row);

    int addUser(Consumer consumer);

    List<Consumer> allUser();
}