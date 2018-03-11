package com.kafka.spring.consumer;

import java.io.Serializable;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class BatchListener {
	
	private static final Logger logger = LoggerFactory.getLogger(BatchListener.class);
	
	@KafkaListener(topics = "test", containerFactory = "batchFactory")
	public void listen(@Payload(required=false) List<String> input,
	        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<Integer> key,
	        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partition,
	        @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topic,
	        @Header(KafkaHeaders.OFFSET) List<Long> offset,
	        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) List<Long> ts) {
		
		logger.info("Consumed input:{} from topic: {} with key: {} ", input, topic,key,offset);
		
	}
	
	//@KafkaListener(topics = "test", containerFactory="kafkaListenerContainerFactory")
	public void listen2(List<ConsumerRecord<String, Serializable>> event) {
		//logger.info("key:{}, value:{}, ",event.key(),event.value());
	}
}
