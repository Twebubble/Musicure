package io.github.Twebubble.musicure.mapper;

import io.github.Twebubble.musicure.model.domain.UserSupport;
import io.github.Twebubble.musicure.service.impl.UserSupportImpl;
import java.util.List;

public interface UserSupportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSupport row);

    int insertSelective(UserSupport row);

    List<UserSupport> selectByExample(UserSupportImpl example);

    UserSupport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserSupport row);

    int updateByPrimaryKey(UserSupport row);
}