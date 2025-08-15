package io.github.Twebubble.musicure.service;

import io.github.Twebubble.musicure.model.domain.Banner;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

//针对表banner(轮播图)的数据库操作Service

public interface BannerService extends IService<Banner> {
    List<Banner> getAllBanner();
}
