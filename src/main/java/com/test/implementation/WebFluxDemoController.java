package com.test.implementation;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.requestreply.RequestReplyMessageFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;

/**
 * Description:  <br>
 * Date: 2022-9-5 16:52<br>
 *
 * @author moon
 * @since 1.0.0
 */
@Component
@RestController
@RequestMapping("/demo")
public class WebFluxDemoController {
    @Autowired
    KafkaUtil kafkaUtil;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private DefaultKafkaProducerFactory defaultKafkaProducerFactory;

    @Autowired
    private KafkaMessageListenerContainer kafkaMessageListenerContainer;
   // private ReplyingKafkaTemplate replyingKafkaTemplate;
    private WebFluxDemoDao webFluxDemoDao;
     public WebFluxDemoController(WebFluxDemoDao webFluxDemoDao) {
        this.webFluxDemoDao = webFluxDemoDao;
        //this.replyingKafkaTemplate =new ReplyingKafkaTemplate(defaultKafkaProducerFactory,kafkaMessageListenerContainer);
    }

    /**
     * 返回字符串
     * @return String
     */
    @GetMapping("/hello/{topic}/{key}/{value}")
    public Mono<String> hello(@PathVariable String topic,@PathVariable String key,@PathVariable String value) {
        kafkaUtil.send(topic, key, value);
        return Mono.just("sent to kafka!");
    }

    @GetMapping("/sum")
    public Mono<String> hello() throws ExecutionException, InterruptedException {
        Message<String> m=new GenericMessage("s");

        /*RequestReplyMessageFuture<String,String> ry= replyingKafkaTemplate.sendAndReceive(m);
        Message<String> mr= (Message<String>) ry.get();*/
        return Mono.just("mr.getPayload()");
    }


    /**
     *  获取对象列表
     * @return 对象list
     */
    @GetMapping("/getall")
    public Flux<WebFluxDemoEntity> list() {
        return webFluxDemoDao.findAll();
    }

    /**
     *  获取单个对象
     * @param id 主键
     * @return 单个对象
     */
    @GetMapping("/{id}")
    public Mono<WebFluxDemoEntity> get(@PathVariable String id) {
        return webFluxDemoDao.findById(id);
    }

    /**
     * 新增/修改
     * @param webFluxDemoEntity 对象
     * @return 新增/修改后的对象
     */
    @PostMapping("/saveone")
    public Mono<WebFluxDemoEntity> save(@RequestBody WebFluxDemoEntity webFluxDemoEntity) {
        return webFluxDemoDao.save(webFluxDemoEntity);
    }

    /**
     * 批量删除
     * @param ids 主键数组
     * @return void
     */
    @DeleteMapping
    public Mono<Void> del(String... ids) {
        return webFluxDemoDao.deleteById(Flux.fromArray(ids));
    }
}

