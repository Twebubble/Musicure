package io.github.Twebubble.musicure.service;

import io.github.Twebubble.musicure.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Integer id);
    User save(User user);
    User update(User user);
    void deleteById(Integer id);
}
