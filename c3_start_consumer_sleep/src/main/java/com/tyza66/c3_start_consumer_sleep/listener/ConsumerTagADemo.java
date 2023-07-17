package com.tyza66.c3_start_consumer_sleep.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * Author: tyza66
 * Date: 2023/7/17 9:54
 * Github: https://github.com/tyza66
 **/

@RocketMQMessageListener(topic = "${rocketmq.consumer.topic}",consumerGroup ="${rocketmq.consumer.group}")
@Component
public class ConsumerTagADemo implements RocketMQListener<String> {

    @Override
    public void onMessage(String o) {
        System.out.println("ConsumerTagADemo onMessage="+o);
        try {
            //这里线程休眠
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
