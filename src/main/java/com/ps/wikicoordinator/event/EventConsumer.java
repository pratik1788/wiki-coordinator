package com.ps.wikicoordinator.event;

import com.ps.wikicoordinator.entity.WikiEventEntity;
import com.ps.wikicoordinator.pojo.EventNotification;
import com.ps.wikicoordinator.repository.WikiEventRepository;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalTime;
import java.time.ZoneId;

@Service
public class EventConsumer {

    @Autowired
    WikiEventRepository wikiEventRepository;

    @KafkaListener(topics = "${application.event.topic}", groupId = "${application.event.group-id}")
    public void consume(byte[] message) throws IOException {
        SpecificDatumReader<EventNotification> reader = new SpecificDatumReader<>(EventNotification.getClassSchema());
        Decoder decoder = DecoderFactory.get().binaryDecoder(message, null);
        EventNotification eventNotification = reader.read(null, decoder);
        wikiEventRepository.save(WikiEventEntity.builder()
                .fileName(eventNotification.getFileName())
                .eventName(eventNotification.getEventName())
                .details(eventNotification.getDetails())
                .eventTime(LocalTime.ofInstant(eventNotification.getEventTimeStamp(), ZoneId.of("UTC")))
                .build()).subscribe();
    }
}
