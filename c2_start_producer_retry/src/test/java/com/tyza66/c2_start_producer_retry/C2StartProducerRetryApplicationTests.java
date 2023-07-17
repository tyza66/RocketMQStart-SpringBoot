package com.tyza66.c2_start_producer_retry;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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
	//不管有没有人接，是否成功 就管发，别的不管
	@Test
	public void testOneWay() {
		rocketMQTemplate.sendOneWay(topic, "这是一条单向消息");
	}

	//测试顺序队列
	//有一种是全局的，有一种局部的 总体来说就是先进先出的表现 谁先放进去就先执行谁
	@Test
	public void testSyncSend() {
		SendResult sendResult = rocketMQTemplate.syncSendOrderly(topic, "这是一条同步顺序消息", "order");

		// 异步顺序消息
		// rocketMQTemplate.asyncSendOrderly();

		// 单向顺序消息
		//  rocketMQTemplate.sendOneWayOrderly(, , , , , );
		System.out.println(sendResult);
	}

	//测试延时消息
	@Test
	public  void testDelay(){
		// 设置延时等级3,这个消息将在10s之后发送(现在只支持固定的几个时间,详看delayTimeLevel)
		SendResult result=rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload("这是一条延时消息").build(),1000,3);
		System.out.println(result);
	}


	//测试批量消息
	@Test
	public  void testBatch(){
		List<Message> messages = new ArrayList<>(); //这里使用的Message是spring中提供的
		messages.add(MessageBuilder.withPayload("Hello 1").build());
		messages.add(MessageBuilder.withPayload("Hello 2").build());
		messages.add(MessageBuilder.withPayload("Hello 6").build());
		rocketMQTemplate.syncSend(topic,messages);
	}

	//测试过滤消息
	@Test
	public void testTag(){
		rocketMQTemplate.syncSend(topic+":tag1",MessageBuilder.withPayload("hello tag1").build());
		rocketMQTemplate.syncSend(topic+":tag2",MessageBuilder.withPayload("hello tag2").build());
		rocketMQTemplate.syncSend(topic+":tag3",MessageBuilder.withPayload("hello tag3").build());
	}


}
