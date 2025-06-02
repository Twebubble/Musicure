package io.github.Twebubble.musicure.service.impl;

import io.github.Twebubble.musicure.entity.User;
import io.github.Twebubble.musicure.mapper.UserMapper;
import io.github.Twebubble.musicure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll(){
        return userMapper.findAll();
    }

    @Override
    public User findById(Integer id){
        return userMapper.findById(id);
    }

    @Override
    public User save(User user){
        userMapper.insert(user);
        return user;
    }

    @Override
    public User update(User user){
        userMapper.update(user);
        return user;
    }

    @Override
    public void deleteById(Integer id){
        userMapper.deleteById(id);
    }
}
