package com.test.implementation;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaUtil {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(KafkaUtil.class);


    /**
     * 向卡夫卡发送消息
     * @param topic 主题
     * @param taskid key
     * @param jsonStr 消息字符串
     */
    public void send(String topic, String taskid, String jsonStr) {
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, taskid, jsonStr);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            //推送成功
            public void onSuccess(SendResult<String, Object> result) {
                logger.info(topic + " 生产者 发送消息成功：" + result.toString());
            }
            @Override
            //推送失败
            public void onFailure(Throwable ex) {
                logger.info(topic + " 生产者 发送消息失败：" + ex.getMessage());
            }
        });
    }

  /*  public void receive(String topic, String taskid, String jsonStr) {
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.receive("talking",);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            //推送成功
            public void onSuccess(SendResult<String, Object> result) {
                logger.info(topic + " 生产者 发送消息成功：" + result.toString());
            }
            @Override
            //推送失败
            public void onFailure(Throwable ex) {
                logger.info(topic + " 生产者 发送消息失败：" + ex.getMessage());
            }
        });
    }*/
    /**
     * 消费kafka里面的消息
     * @param record
     */
    //下面的主题是一个数组，可以同时订阅多主题，只需按数组格式即可，也就是用“，”隔开
    @KafkaListener(topics = {"talking"})
    @SendTo
    public Message<String> receive(ConsumerRecord<String, ?> record){
        logger.info("消费得到的消息1---key: " + record.key());
        logger.info("消费得到的消息1---value: " + record.value().toString());
        return new GenericMessage("receive1");
    }

    /**
     * 消费kafka里面的消息
     * @param record
     */
    //下面的主题是一个数组，可以同时订阅多主题，只需按数组格式即可，也就是用“，”隔开
    @KafkaListener(topics = {"talking"})
    @SendTo
    public Message<String> receive2(ConsumerRecord<?, ?> record){
        logger.info("消费得到的消息2---key: " + record.key());
        logger.info("消费得到的消息2---value: " + record.value().toString());
        return new GenericMessage("receive2");
    }

}

