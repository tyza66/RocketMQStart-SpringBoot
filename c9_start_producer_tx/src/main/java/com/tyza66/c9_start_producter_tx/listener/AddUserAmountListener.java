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
public class AddUserAmountListener implements RocketMQLocalTransactionListener {

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        log.info("执行本地事务");
        MessageHeaders headers = msg.getHeaders();

        //获取事务ID
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        log.info("事务ID是{}",transactionId);
        System.out.println(msg.getPayload().toString());

        //获得payload
        byte[] payloadByte = (byte[]) msg.getPayload();
        String payload = new String(payloadByte);
        log.info("事务payload是{}",payload);

        //获取自己添加的my_data
        String myData = headers.get("my_data").toString();
        log.info("myData是{}",myData);

        //获取第三个参数里面放的
        String argData = arg.toString();
        log.info("argData是{}",argData);

        try{
            //执行本地事务，并记录日志
            int a = 1;
            int b = a/0; //异常开关
            //执行成功，可以提交事务
            log.info("本地事务执行成功");
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e){
            log.info("本地事务执行失败");
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 本地事务的检查，检查本地事务是否成功
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {

        MessageHeaders headers = message.getHeaders();
        //获取事务ID
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        log.info("检查本地事务,事务ID:{}",transactionId);
        //检查事务是否执行成功
        boolean success = true;
        if(success){
            log.info("本地事务检查结果成功");
            return RocketMQLocalTransactionState.COMMIT;
        }
        log.info("本地事务检查结果失败");
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
