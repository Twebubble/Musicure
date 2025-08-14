package io.github.Twebubble.musicure.service;

import io.github.Twebubble.musicure.common.R;
import io.github.Twebubble.musicure.model.domain.Admin;
import io.github.Twebubble.musicure.model.request.AdminRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpSession;

public interface AdminService extends IService<Admin> {

    R verityPasswd(AdminRequest adminRequest, HttpSession session);
}
