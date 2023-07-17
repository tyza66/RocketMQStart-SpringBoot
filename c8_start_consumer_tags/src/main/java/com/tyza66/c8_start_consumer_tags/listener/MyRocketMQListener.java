package com.tyza66.c8_start_consumer_tags.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * Author: tyza66
 * Date: 2023/7/17 16:15
 * Github: https://github.com/tyza66
 **/

//这个消费者只消费有tag1和tag2的消息
@Component
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = "${rocketmq.consumer.topic}",selectorExpression = "tag1 || tag2",selectorType = SelectorType.TAG)
public class MyRocketMQListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String msg) {
        System.out.println(msg);
    }
}

