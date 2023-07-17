package com.tyza66.c1_start_producer.sender;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author: tyza66
 * Date: 2023/7/17 10:05
 * Github: https://github.com/tyza66
 **/

@Component
public class ProducerDemo {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    // 发送消息的实例
    public void sendMessage(String topic, String msg) {
        rocketMQTemplate.convertAndSend(topic, msg);
    }
}
