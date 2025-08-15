package io.github.Twebubble.musicure.controller;

import io.github.Twebubble.musicure.common.R;
import io.github.Twebubble.musicure.model.request.CollectRequest;
import io.github.Twebubble.musicure.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CollectController {

    @Autowired
    private CollectService collectService;

    //前台界面逻辑
    //添加收藏的歌曲
    @PostMapping("/collection/add")
    public R addCollection(@RequestBody CollectRequest addCollectRequest){
        return collectService.addCollection(addCollectRequest);
    }

    //取消收藏的歌曲
    @PostMapping("/collection/delete")
    public R deleteCollection(@RequestParam Integer userId, @RequestParam Integer songId) {
        return collectService.deleteCollect(userId, songId);
    }

    //是否收藏歌曲
    @PostMapping("/collection/status")
    public R isCollection(@RequestBody CollectRequest isCollectRequest) {
        return collectService.existSongId(isCollectRequest);
    }

    //返回指定用户ID收藏的列表
    @GetMapping("/collection/detail")
    public R collectionOfUser(@RequestParam Integer userId) {
        return collectService.collectionOfUser(userId);
    }

}
