package com.tyza66.c1_start_producer;

import com.tyza66.c1_start_producer.sender.ProducerDemo;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = C1StartProducerApplication.class)
class RocketmqApplicationTests {

	@Value("${rocketmq.consumer.topic}")
	private String topic;

	@Autowired
	private ProducerDemo producerDemo;

	@Test
	public void testProducer(){
		String msg =  UUID.randomUUID().toString();
		producerDemo.sendMessage(topic , "Hello,World!");
	}
}
