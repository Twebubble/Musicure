package io.github.Twebubble.musicure.controller;

import io.github.Twebubble.musicure.service.impl.ConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping
@Controller
public class ConsumerController {

    @Autowired
    private ConsumerServiceImpl consumerService;

    @RequestMapping(value = "/allUSer", method = RequestMethod.GET)
    public Object allUser(){
        return consumerService.allUser();
    }
}
