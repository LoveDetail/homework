package com.gp.distribution.kafka.subscribe;

import cn.hutool.core.util.StrUtil;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

/**
 * Create by Wayne on 2020/7/28
 */
public class SubcribeKafkaConsumer {

    private static KafkaConsumer kafkaConsumer1 ;
    private static KafkaConsumer kafkaConsumer2 ;
    private static KafkaConsumer kafkaConsumer3 ;

    private static String topicName = "ycLogTopic" ;

    static{
        Properties properties=new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.2.61:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"KafkaConsumerDemo1");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");









        kafkaConsumer1=new KafkaConsumer(properties);
        kafkaConsumer1.subscribe(Arrays.asList(topicName));

        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"KafkaConsumerDemo2");
        kafkaConsumer2=new KafkaConsumer(properties);
        kafkaConsumer2.subscribe(Collections.singletonList(topicName));

        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"KafkaConsumerDemo3");
        kafkaConsumer3=new KafkaConsumer(properties);
        kafkaConsumer3.subscribe(Collections.singletonList(topicName));

    }


    public static void main(String[] args) {
        try {
            while(true){

                ConsumerRecords<String,String> consumerRecord1=kafkaConsumer1.poll(1);
                for(ConsumerRecord record:consumerRecord1){

                    if(!record.key().equals("0000")){
                        kafkaConsumer1.commitAsync();
                        continue;
                    }


                    System.out.println(StrUtil.format("11111 topicName:{} message receive key:{}   value:{}",record.topic(),record.key(),record.value()));
                    kafkaConsumer1.commitAsync();
                }


                ConsumerRecords<String,String> consumerRecord2=kafkaConsumer2.poll(1);
                for(ConsumerRecord record:consumerRecord2){
                    if(!record.key().equals("0001")){
                        kafkaConsumer2.commitAsync();
                        continue;
                    }

                    System.out.println(StrUtil.format("22222 topicName:{} message receive key:{}   value:{}",record.topic(),record.key(),record.value()));
                    kafkaConsumer2.commitAsync();
                }


                ConsumerRecords<String,String> consumerRecord3=kafkaConsumer3.poll(1);
                for(ConsumerRecord record:consumerRecord3){
                    if(!record.key().equals("0002")){
                        kafkaConsumer3.commitAsync();
                        continue;
                    }

                    System.out.println(StrUtil.format("33333 topicName:{} message receive key:{}   value:{}",record.topic(),record.key(),record.value()));
                    kafkaConsumer3.commitAsync();
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
