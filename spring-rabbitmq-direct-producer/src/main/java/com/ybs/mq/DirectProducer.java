package com.ybs.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * Producer
 *
 * @author Paulson
 * @date 2020/4/6 23:10
 */

@Component
public class DirectProducer {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    /**
     *  定义交换机
     */

    @Value("${order.direct.exchange}")
    private String exchangeName;

    /**
     *定义路由Key
     */
    private static String routeKey = "";

    /**
     * 发送消息
     */
    public void sendMessage(){
        String orderId = UUID.randomUUID().toString();
        String message = "您的订单信息是: " + orderId + ", 日期是: " + new Date();
        rabbitTemplate.convertAndSend(exchangeName, "email", message);
    }

    public void sendMessage(int i){
        String orderId = UUID.randomUUID().toString();
        String message = i + "您的订单信息是: " + orderId + ", 日期是: " + new Date();
        rabbitTemplate.convertAndSend(exchangeName, "log", message);
        rabbitTemplate.convertAndSend(exchangeName, "email", message);
        rabbitTemplate.convertAndSend(exchangeName, "wechat", message);


        // 一次发送多个 主题模式 topic


    }


}
