package io.github.Twebubble.musicure.service.impl;

import io.github.Twebubble.musicure.common.R;
import io.github.Twebubble.musicure.mapper.CollectMapper;
import io.github.Twebubble.musicure.model.domain.Collect;
import io.github.Twebubble.musicure.model.request.CollectRequest;
import io.github.Twebubble.musicure.service.CollectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService{

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public R addCollection(CollectRequest addCollectRequest) {
        Collect collect = new Collect();
        BeanUtils.copyProperties(addCollectRequest, collect);
        if (collectMapper.insert(collect) >0 ){
            return R.success("收藏成功", true);
        } else {
            return R.error("收藏失败");
        }
    }

    @Override
    public R existSongId(CollectRequest isCollectRequest){
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",isCollectRequest.getUserId());
        queryWrapper.eq("song_id",isCollectRequest.getSongId());
        if (collectMapper.selectCount(queryWrapper) > 0) {
            return R.success("已收藏", true);
        } else {
            return R.success("未收藏", false);
        }
    }

    @Override
    public R deleteCollect(Integer userId, Integer songId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("song_id",songId);
        if (collectMapper.delete(queryWrapper) > 0) {
            return R.success("取消收藏", false);
        } else {
            return R.error("取消收藏失败");
        }
    }

    @Override
    public R collectionOfUser(Integer userId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return R.success("用户收藏", collectMapper.selectList(queryWrapper));
    }
}