package io.github.Twebubble.musicure.service;

import io.github.Twebubble.musicure.model.domain.UserSongPreference;

import java.util.List;

public interface UserBehaviorService  {

    List<UserSongPreference> getQuantifiedPreferences();

//    double getBehaviorWeight(Integer behaviorType);

}
