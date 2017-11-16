package com.test.eventtracker.service;


import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: sbenner
 * Date: 10/19/17
 * Time: 12:15 AM
 */
@Component
public class KafkaService {
    private final static Logger logger = LoggerFactory.getLogger(KafkaService.class);


    @Value("${kafka.topic:events}")
    private String kafkaTopic;

    @Autowired
    Producer<Long, String> kafkaProducer;

    public void sendMsgToKafka(String msg) throws Exception {

        long time = System.currentTimeMillis();

        try {

            final ProducerRecord<Long, String> record =
                    new ProducerRecord<>(kafkaTopic, msg);

           

            RecordMetadata metadata = kafkaProducer.send(record).get();

            long elapsedTime = System.currentTimeMillis() - time;
            String str = String.format("sent record(key=%s value=%s) " +
                            "meta(partition=%d, offset=%d) time=%d",
                    record.key(), record.value(), metadata.partition(),
                    metadata.offset(), elapsedTime);
            logger.info(str);


        } finally {
            kafkaProducer.flush();
         //   kafkaProducer.close();
        }
    }

}
