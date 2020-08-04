package com.gp.distribution.kafka.subscribe;

import cn.hutool.core.util.StrUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Create by Wayne on 2020/7/28
 */
public class SubcrisbeKafkaProducer {

    private static final KafkaProducer producer ;
    private static final String topicName ;

    static{
        topicName = "ycLogTopic" ;

        Properties properties=new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "121.36.48.218:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"KafkaProducerDemo");
        properties.put(ProducerConfig.ACKS_CONFIG,"-1");
        properties.put(ProducerConfig.RETRIES_CONFIG,"3") ;
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        producer= new KafkaProducer(properties);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        for(int i=0; i<20; i++){
            ProducerRecord<String,String> record = new ProducerRecord<>(topicName,"000"+i%3, StrUtil.format("{}===logstale==={}",i,i%3)) ;

            Future<RecordMetadata> future = producer.send(record) ;
            RecordMetadata metaDate =  future.get();

            System.out.println(StrUtil.format("topic=={}  分区==={}  偏移量 =={}",metaDate.topic(),metaDate.partition(),metaDate.offset()));
            System.out.println(metaDate.toString());
        }
    }










}
