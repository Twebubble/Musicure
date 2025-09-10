package io.github.Twebubble.musicure.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import io.github.Twebubble.musicure.model.domain.Emotion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmotionMapper extends BaseMapper<Emotion> {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(Emotion row);
//
//    int insertSelective(Emotion row);
//
//    List<Emotion> selectByExample(EmotionServiceImpl example);
//
//    Emotion selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(Emotion row);
//
//    int updateByPrimaryKey(Emotion row);

    Emotion selectLatestEmotionByUserId(Long userId);

}