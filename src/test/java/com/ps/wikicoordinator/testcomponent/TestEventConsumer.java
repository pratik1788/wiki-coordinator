package com.ps.wikicoordinator.testcomponent;

import com.ps.wikicoordinator.event.EventConsumer;
import com.ps.wikicoordinator.pojo.EventNotification;
import lombok.Getter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class TestEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);

    List<EventNotification> eventNotifications =new ArrayList<>();

    @KafkaListener(topics = "${application.event.topic}", groupId = "Test")
    public void consume(byte[] message,  @Header(KafkaHeaders.OFFSET) Integer offset) throws IOException {

        SpecificDatumReader<EventNotification> reader = new SpecificDatumReader<>(EventNotification.getClassSchema());
        Decoder decoder = DecoderFactory.get().binaryDecoder(message, null);
        EventNotification eventNotification = reader.read(null, decoder);
        eventNotifications.add(eventNotification);

    }
}
