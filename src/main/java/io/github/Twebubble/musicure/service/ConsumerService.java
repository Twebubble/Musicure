package io.github.Twebubble.musicure.service;

import io.github.Twebubble.musicure.model.domain.Consumer;

import java.util.List;

public interface ConsumerService {

    boolean addUser(Consumer consumer);

    List<Consumer> allUser();
}
