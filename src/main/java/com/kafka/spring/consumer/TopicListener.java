package com.kafka.spring.consumer;

import java.io.Serializable;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TopicListener {
	
	private static final Logger logger = LoggerFactory.getLogger(TopicListener.class);
	
	@KafkaListener(topics = "test",clientIdPrefix = "testListener")
	public void listen(@Payload String input,
	        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key,
	        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
	        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
	        @Header(KafkaHeaders.OFFSET) long offset,
	        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {
		
		logger.info("Consumed input:{} from topic: {} with key: {} offset: {} receive time:{}", input, topic,key,offset,ts);
		
	}
	
	//@KafkaListener(topics = "test", containerFactory="kafkaListenerContainerFactory")
	public void listen2(ConsumerRecord<String, Serializable> event) {
		logger.info("key:{}, value:{} offset:{} receive tme:{}, ",event.key(),event.value(),event.offset(), event.timestamp());
	}
}
