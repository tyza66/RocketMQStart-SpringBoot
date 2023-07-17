package com.tyza66.c2_start_producer_retry;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class C2StartProducerRetryApplicationTests {
	@Value("${rocketmq.consumer.topic}")
	private String topic;

	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	@Test
	void contextLoads() {
		rocketMQTemplate.convertAndSend(topic,"giao");
	}

}
