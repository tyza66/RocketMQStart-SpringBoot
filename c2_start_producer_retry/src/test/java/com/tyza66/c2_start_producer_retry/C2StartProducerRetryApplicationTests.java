package com.tyza66.c2_start_producer_retry;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
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

	//测试自动重试的发送消息（在配置文件中配置了重试的相关性信息）
	@Test
	void testRetry() {
		rocketMQTemplate.convertAndSend(topic, "giao");
	}

	//测试同步消息
	//消息发送方发出数据后，会在收到接收方发回响应之后才发下一个数据包的通讯方式
	@Test
	void testSync() {
		SendResult sendResult = rocketMQTemplate.syncSend(topic, "这是一条同步消息");
		System.out.println(sendResult);
	}

	//测试异步消息
	//发送方发出数据后，不等接收方发回响应，接着发送下个数据包的通讯方式。发送方通过回调接口接收服务器响应，并对响应结果进行处理
	@Test
	public void testAsyncSend() throws InterruptedException {
		rocketMQTemplate.asyncSend(topic, "这是一条异步消息", new
				SendCallback() {
					@Override
					public void onSuccess(SendResult sendResult) {
						System.out.println(sendResult);
					}

					@Override
					public void onException(Throwable throwable) {
						System.out.println(throwable);
					}
				});
		Thread.sleep(10000);
	}

	//测试单向消息
	@Test
	public void testOneWay() {
		rocketMQTemplate.sendOneWay(topic, "这是一条单向消息");
	}

	
}
