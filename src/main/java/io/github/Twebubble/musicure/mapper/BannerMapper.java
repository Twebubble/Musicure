package io.github.Twebubble.musicure.mapper;

import io.github.Twebubble.musicure.model.domain.Banner;
import io.github.Twebubble.musicure.service.impl.BannerImpl;
import java.util.List;

public interface BannerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Banner row);

    int insertSelective(Banner row);

    List<Banner> selectByExample(BannerImpl example);

    Banner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Banner row);

    int updateByPrimaryKey(Banner row);
}