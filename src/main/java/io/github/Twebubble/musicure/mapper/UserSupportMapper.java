package io.github.Twebubble.musicure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.Twebubble.musicure.model.domain.UserSupport;
import io.github.Twebubble.musicure.service.impl.UserSupportServiceImpl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserSupportMapper extends BaseMapper<UserSupport> {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSupport row);

    int insertSelective(UserSupport row);

    List<UserSupport> selectByExample(UserSupportServiceImpl example);

    UserSupport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserSupport row);

    int updateByPrimaryKey(UserSupport row);
}