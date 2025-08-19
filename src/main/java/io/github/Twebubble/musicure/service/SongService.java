package io.github.Twebubble.musicure.service;

import io.github.Twebubble.musicure.common.R;
import io.github.Twebubble.musicure.model.domain.Song;
import io.github.Twebubble.musicure.model.request.SongRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

public interface SongService extends IService<Song> {

    R addSong (SongRequest addSongRequest, MultipartFile lrcfile, MultipartFile mpfile);

    R updateSongMsg(SongRequest updateSongRequest);

    R updateSongUrl(MultipartFile urlFile, int id);

    R updateSongPic(MultipartFile urlFile, int id);

    R deleteSong(Integer id);

    R allSong();

    R songOfSingerId(Integer singerId);

    R songOfId(Integer id);

    R songOfSingerName(String name);

    R updateSongLrc(MultipartFile lrcFile, int id);

}
