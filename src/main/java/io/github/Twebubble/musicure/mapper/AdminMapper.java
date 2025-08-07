package io.github.Twebubble.musicure.mapper;

import io.github.Twebubble.musicure.model.domain.Admin;
import io.github.Twebubble.musicure.service.impl.AdminImpl;
import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin row);

    int insertSelective(Admin row);

    List<Admin> selectByExample(AdminImpl example);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin row);

    int updateByPrimaryKey(Admin row);
}