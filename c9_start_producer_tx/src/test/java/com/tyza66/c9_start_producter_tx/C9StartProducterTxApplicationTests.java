package com.tyza66.c9_start_producter_tx;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;

import java.util.HashMap;
import java.util.UUID;

@SpringBootTest
class C9StartProducterTxApplicationTests {
	@Value("${rocketmq.consumer.topic}")
	private String topic;

	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	@Test
	public void testRocketTx() throws InterruptedException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", "小白");
		map.put("age", 22);
		map.put("sex", "男");
		rocketMQTemplate.sendMessageInTransaction(topic,
				MessageBuilder.withPayload(map)
						.setHeader(RocketMQHeaders.TRANSACTION_ID, UUID.randomUUID().toString())
						.setHeader("my_data", map)
						.build(), map
		);
		System.out.println("发送了消息");
	}


}
