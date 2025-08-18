package io.github.Twebubble.musicure.service;

import io.github.Twebubble.musicure.model.domain.Order;

//关于邮箱信息发送

public interface OrderManager {

    void sendPassword(Order order,String receiveAddress);

    void sendCode(String code,String receiveAddress);

}
