package io.github.Twebubble.musicure.service;

import io.github.Twebubble.musicure.common.R;
import io.github.Twebubble.musicure.model.domain.Singer;
import io.github.Twebubble.musicure.model.request.SingerRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

public interface SingerService extends IService<Singer> {

    R addSinger (SingerRequest addSingerRequest);

    R updateSingerMsg(SingerRequest updateSingerRequest);

    R updateSingerPic(MultipartFile avatorFile, int id);

    R deleteSinger(Integer id);

    R allSinger();

    R singerOfName(String name);

    R singerOfSex(Integer sex);

}
