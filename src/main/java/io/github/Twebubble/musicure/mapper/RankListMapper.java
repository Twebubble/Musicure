package io.github.Twebubble.musicure.mapper;

import io.github.Twebubble.musicure.model.domain.RankList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RankListMapper extends BaseMapper<RankList> {

    //查总评分
    int selectScoreSum(Long songListId);

    //查指定用户评分
    Integer selectUserRank(@Param("consumer_id") Long consumerId, @Param("song_list_id")  Long songListId);

//    int deleteByPrimaryKey(Long id);
//
//    int insert(RankList row);
//
//    int insertSelective(RankList row);
//
//    List<RankList> selectByExample(RankListServiceImpl example);
//
//    RankList selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(RankList row);
//
//    int updateByPrimaryKey(RankList row);
}