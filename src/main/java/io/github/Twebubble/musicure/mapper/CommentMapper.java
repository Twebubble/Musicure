package io.github.Twebubble.musicure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.Twebubble.musicure.model.domain.Comment;
import io.github.Twebubble.musicure.service.impl.CommentServiceImpl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment row);

    int insertSelective(Comment row);

    List<Comment> selectByExample(CommentServiceImpl example);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment row);

    int updateByPrimaryKey(Comment row);
}