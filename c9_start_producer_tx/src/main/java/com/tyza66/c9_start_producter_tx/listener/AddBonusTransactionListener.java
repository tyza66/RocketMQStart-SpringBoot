package com.tyza66.c9_start_producter_tx.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: tyza66
 * Date: 2023/7/17 16:47
 * Github: https://github.com/tyza66
 **/


@Slf4j
@RocketMQTransactionListener
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBonusTransactionListener implements RocketMQLocalTransactionListener {

    @Transactional(rollbackFor = Exception.class)//这里声明了事务
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        log.info("本地事务执行");
        MessageHeaders headers = msg.getHeaders();

        byte[] payloadByte = (byte[]) msg.getPayload();
        String payload = new String(payloadByte);
        System.out.println("payload = " + payload);

        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        System.out.println("transactionId = " + transactionId);

        String myData = headers.get("my_data").toString();
        System.out.println("myData = " + myData);

        String argData = arg.toString();
        System.out.println("argData = " + argData);

        try {
            // TODO 记录MQ日志
            int a = 10;
            int b = a / 0; //这里触发错误
            //可以消费该消息
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            // 继续查询该消息的状态
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }

    /**
     * 检查本地事务状态
     *
     * @param msg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        MessageHeaders headers = msg.getHeaders();
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);

        // TODO 根据事务id查询数据库,判断消息是否消费
        Object DbRocketMQLog = "DB RocketMQ Log Object";

        if (DbRocketMQLog != null) {
            // 消息已被消费,删除该消息
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        // TODO 进行消费逻辑,记录MQ日志
        return RocketMQLocalTransactionState.COMMIT;
    }
}
