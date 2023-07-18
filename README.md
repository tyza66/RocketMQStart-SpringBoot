# RocketMQStart-SpringBoot
### RocketMQ入门（Spring Boot环境）
##### 说明
- 官网文档和网上的视频要么看起来十分困难，要么根本不全，这个教程只着重于使用，简单不绕
- 这是一个直接使用Spring Boot整合RocketMQ的教程
- 其中的项目区分是依据各种配置的可复用程度区分的，学习时按照章节目录寻找项目文件即可
- 章节名称中的C指的是课程（class），表示的是学习和理解的顺序，和项目目录无关
- 在第几课的时候创建的项目目录名称开头就是C几

##### 章节
- C1：简单队列消息收发：在生产者中使用rocketMQTemplate发送消息(c1_start_consumer、c1_start_producer)
- C2：生产者多次重试：配置了如果发送失败就进行多次尝试(c1_start_consumer、c2_start_producer_retry)
- C3：同步消息和异步消息：同步的情况下收到接收方发回响应之后才发下一个数据包的通讯方式（并不是说的消费者，而是请求接收），异步不用(c1_start_consumer、c2_start_producer_retry、c3_start_consumer_sleep)
- C4：单向消息：发送方只负责发送消息，不等待服务器回应且没有回调函数触发，即只发送请求不等待应答(c1_start_consumer、c2_start_producer_retry、c3_start_consumer_sleep)
- C5：顺序消息：消息有序指的是可以按照消息的发送顺序来消费，当发送和消费参与的queue只有一个，则是全局有序。如果多个queue参与，则为分区有序，即相对每个queue，消息都是有序的(c1_start_consumer、c2_start_producer_retry、c3_start_consumer_sleep)
- C6：延时消息：设置消息在一定的时间之后发送，这个时间不能自定义，这里面有18个延时等级（1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h）(c1_start_consumer、c2_start_producer_retry、c3_start_consumer_sleep)
- C7：批量消息：就是多个消息一起发送(c1_start_consumer、c2_start_producer_retry、c3_start_consumer_sleep)
- C8：过滤消息：就是在消息的标题后面加上":tag"，之后消费者接收的时候按照不同的tag指定的的监听器进行接收（就是说这类消费者只接收相关类型的消息）(c1_start_consumer、c2_start_producer_retry、c3_start_consumer_sleep、c8_start_consumer_tags)
- C9：*事务消息*：RocketMQ提供了事务消息，通过事务消息就能达到分布式事务的最终一致，甚至可以用事务消息实现分布式事务（事务消息共有三种状态：提交状态TransactionStatus.CommitTransaction、回滚状态TransactionStatus.RollbackTransaction、中间状态TransactionStatus.Unknown）(c1_start_consumer、c9_start_producer_tx)
![事务消息](./资料/事务消息.awebp)

By：tyza66
