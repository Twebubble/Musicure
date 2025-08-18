package io.github.Twebubble.musicure.service;

import io.github.Twebubble.musicure.common.R;
import io.github.Twebubble.musicure.model.domain.ListSong;
import io.github.Twebubble.musicure.model.request.ListSongRequest;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ListSongService extends IService<ListSong> {

    R addListSong(ListSongRequest addListSongRequest);

    R updateListSongMsg(ListSongRequest updateListSongRequest);

    R deleteListSong(Integer songId);

    //TODO 未实现的方法
    List<ListSong> allListSong();

    R listSongOfSongId(Integer songListId);
}
