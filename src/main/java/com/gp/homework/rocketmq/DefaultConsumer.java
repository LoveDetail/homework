package com.gp.homework.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Create by Wayne on 2020/3/19
 */
public class DefaultConsumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer=
                new DefaultMQPushConsumer("gp_consumer_group");

        consumer.setNamesrvAddr("192.168.0.32:9876;192.168.0.72:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.subscribe("Wayne_Test_Topic","*");
        DefaultMQPullConsumer s = new DefaultMQPullConsumer("") ;

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
                                                            ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println("Receive Message: "+list);

                return ConsumeConcurrentlyStatus.RECONSUME_LATER; //返回重试
            }
        });

//        consumer.registerMessageListener(new MessageListenerOrderly() {
//            @Override
//            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
//
//                MessageExt  messageExt=list.get(0);
//                //TODO  --
//                // Throw Exceptio
//                // 重新发送该消息
//                // DLQ（通用设计）
//                if(messageExt.getReconsumeTimes()==3){  //消息重发了三次
//                    //持久化 消息记录表
//                    return ConsumeOrderlyStatus.SUCCESS; //签收
//                }
//                return ConsumeOrderlyStatus.SUCCESS; //签收
//            }
//        });

        consumer.start();

    }
}
