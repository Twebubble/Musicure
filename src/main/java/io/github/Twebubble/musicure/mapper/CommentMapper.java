package io.github.Twebubble.musicure.mapper;

import io.github.Twebubble.musicure.model.domain.Comment;
import io.github.Twebubble.musicure.service.impl.CommentImpl;
import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment row);

    int insertSelective(Comment row);

    List<Comment> selectByExample(CommentImpl example);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment row);

    int updateByPrimaryKey(Comment row);
}