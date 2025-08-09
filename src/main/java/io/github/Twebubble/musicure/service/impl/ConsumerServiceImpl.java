package io.github.Twebubble.musicure.service.impl;

import io.github.Twebubble.musicure.mapper.ConsumerMapper;
import io.github.Twebubble.musicure.model.domain.Consumer;
import io.github.Twebubble.musicure.service.ConsumerService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService{
    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public boolean addUser(Consumer consumer)
    {
        return consumerMapper.addUser(consumer) > 0? true : false;
    }

    @Override
    public List<Consumer> allUser(){
        return consumerMapper.allUser();
    }
}