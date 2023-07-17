# RocketMQStart-SpringBoot
### RocketMQ入门（Spring Boot环境）
##### 说明
- 官网文档和网上的视频要么看起来十分困难，要么根本不全，这个教程只着重于使用，简单不绕
- 这是一个直接使用Spring Boot整合RocketMQ的教程
- 其中的项目区分是依据各种配置的可复用程度区分的，学习时按照章节目录寻找项目文件即可
- 章节名称中的C指的是课程（class），表示的是学习和理解的顺序，和项目目录无关

##### 章节
- C1：简单队列消息收发：在生产者中使用rocketMQTemplate发送消息(c1_start_consumer、c1_start_consumer)
- C2：生产者多次重试：配置了如果发送失败就进行多次尝试(c1_start_consumer、c2_start_producer_retry)
- C3：同步消息和异步消息：同步的情况下收到接收方发回响应之后才发下一个数据包的通讯方式，异步不用(c1_start_consumer、c2_start_producer_retry、c3_start_consumer_sleep)
- C4：单向消息：发送方只负责发送消息，不等待服务器回应且没有回调函数触发，即只发送请求不等待应答(c1_start_consumer、c2_start_producer_retry、c3_start_consumer_sleep)
- C5：顺序消息：

By：tyza66
