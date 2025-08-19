package io.github.Twebubble.musicure.service;

import io.github.Twebubble.musicure.common.R;
import io.github.Twebubble.musicure.model.domain.UserSupport;
import io.github.Twebubble.musicure.model.request.UserSupportRequest;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserSupportService extends IService<UserSupport> {

    R isUserSupportComment(UserSupportRequest userSupportRequest);

    R insertCommentSupport(UserSupportRequest userSupportRequest);

    R deleteCommentSupport(UserSupportRequest userSupportRequest);

}
