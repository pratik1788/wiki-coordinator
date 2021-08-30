package com.ps.wikicoordinator.repository;

import com.ps.wikicoordinator.AbstractWikiCoordinatorApplicationTests;
import com.ps.wikicoordinator.entity.WikiEventEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class WikiEventRepositoryTest extends AbstractWikiCoordinatorApplicationTests {

    @Autowired
    private WikiEventRepository wikiEventRepository;

    @Test
    void findByFileName() {
        Mono<WikiEventEntity> data= wikiEventRepository.save(WikiEventEntity.builder()
                .fileName("Test")
                .eventName("readingStarted")
                .details("test detail")
                .eventTime(LocalTime.now())
                .build());

        StepVerifier.create(data).expectNextCount(1).verifyComplete();
    }
}