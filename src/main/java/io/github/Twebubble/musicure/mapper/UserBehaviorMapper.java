package io.github.Twebubble.musicure.mapper;

import io.github.Twebubble.musicure.model.domain.UserSongBehavior;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBehaviorMapper {
    List<UserSongBehavior> listUserSongBehaviors();
}
