package io.github.Twebubble.musicure.config;

import io.github.Twebubble.musicure.constant.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 集中图像的配置类，将URL请求路径映射到本地文件系统的实际路径。
// 浏览器请求 http://domain.com/img/avatorImages/user1.jpg
// →实际从服务器磁盘的 /项目路径/img/avatorImages/user1.jpg 加载文件。

@Configuration
public class WebPicConfig implements WebMvcConfigurer {

    //这个配置类的目的是：注册了一个类似于拦截器，看到对应的资源，会将其修改成相应的地址
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/avatorImages/**")
                .addResourceLocations(Constants.AVATOR_IMAGES_PATH);
        registry.addResourceHandler("/img/singerPic/**")
                .addResourceLocations(Constants.SINGER_PIC_PATH);
        registry.addResourceHandler("/img/songPic/**")
                .addResourceLocations(Constants.SONG_PIC_PATH);
        registry.addResourceHandler("/song/**")
                .addResourceLocations(Constants.SONG_PATH);
        registry.addResourceHandler("/img/songListPic/**")
                .addResourceLocations(Constants.SONGLIST_PIC_PATH);
        registry.addResourceHandler("/img/swiper/**")
                .addResourceLocations(Constants.BANNER_PIC_PATH);
    }

}