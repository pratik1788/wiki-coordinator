package com.ps.wikicoordinator.event;

import com.ps.wikicoordinator.AbstractWikiCoordinatorApplicationTests;
import com.ps.wikicoordinator.entity.WikiEventEntity;
import com.ps.wikicoordinator.pojo.EventNotification;
import com.ps.wikicoordinator.repository.WikiEventRepository;
import com.ps.wikicoordinator.testcomponent.TestEventProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventConsumerTest extends AbstractWikiCoordinatorApplicationTests {

    @Autowired
    private TestEventProducer testEventProducer;

    @Autowired
    private WikiEventRepository wikiEventRepository;

    @Autowired
    EventConsumer eventConsumer;

    @Test
    void consume() throws Exception {
        wikiEventRepository.deleteAll().block();
        testEventProducer.sendMessage(EventNotification.newBuilder().setEventName("TestEvent").setDetails("TestDetail").setEventTimeStamp( Instant.now())
                .setFileName("TestFile").build());
        Thread.sleep(10000);
        List<WikiEventEntity> wikiEventEntityFlux = wikiEventRepository.findByFileName(Mono.just("TestFile")).collectList().block();
        Assertions.assertEquals(1,wikiEventEntityFlux.size());
    }
}