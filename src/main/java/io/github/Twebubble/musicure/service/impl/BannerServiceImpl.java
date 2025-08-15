package io.github.Twebubble.musicure.service.impl;

import io.github.Twebubble.musicure.mapper.BannerMapper;
import io.github.Twebubble.musicure.model.domain.Banner;
import io.github.Twebubble.musicure.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    //首次调用：执行方法体 → 查询数据库 → 结果存入缓存，打印“没有走缓存”
    //后续调用：直接从缓存返回结果（跳过方法体）
    @Cacheable(value = "banner", key = "'list'")
    @Override
    public List<Banner> getAllBanner(){
        System.out.println("没有走缓存");
        return bannerMapper.selectList(null);
    }
}