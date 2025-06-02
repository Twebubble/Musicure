package io.github.Twebubble.musicure.mapper;

import io.github.Twebubble.musicure.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<User> findAll();
    User findById(Integer id);
    int insert(User user);
    int update(User user);
    int deleteById(Integer id);

}
