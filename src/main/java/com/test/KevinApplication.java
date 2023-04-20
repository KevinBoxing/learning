package com.test;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class KevinApplication {
    // 这是核心的ReplyingKafkaTemplate
    /*@Bean
    public ReplyingKafkaTemplate<String, Model, Model> replyKafkaTemplate(ProducerFactory<String, Model> pf, KafkaMessageListenerContainer<String, Model> container) {
        return new ReplyingKafkaTemplate<>(pf, container);
    }
    // 配件：监听器容器Listener Container to be set up in ReplyingKafkaTemplate
    @Bean
    public KafkaMessageListenerContainer<String, Model> replyContainer(ConsumerFactory<String, Model> cf) {
        ContainerProperties containerProperties = new ContainerProperties(requestReplyTopic);
        return new KafkaMessageListenerContainer<>(cf, containerProperties);
    }
    // 配件：生产者工厂Default Producer Factory to be used in ReplyingKafkaTemplate
    @Bean
    public ProducerFactory<String,Model> producerFactory() {
        return new DefaultKafkaProducerFactory<>(new ProducerConfig());
    }
    //消费者工厂 Default Consumer Factory
    @Bean
    public ConsumerFactory<String, Model> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(),new StringDeserializer(),new JsonDeserializer<>(Model.class));
    }
    // 并发监听器容器Concurrent Listner container factory
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Model>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Model> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        // NOTE - set up of reply template 设置响应模板
        factory.setReplyTemplate(kafkaTemplate());
        return factory;
    }
    // Standard KafkaTemplate
    @Bean
    public KafkaTemplate<String, Model> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }*/
    public static void main(String[] args){
        SpringApplication.run(KevinApplication.class,args);
    }
}
