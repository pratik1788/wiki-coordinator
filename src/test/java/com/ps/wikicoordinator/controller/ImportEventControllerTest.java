package com.ps.wikicoordinator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.wikicoordinator.AbstractWikiCoordinatorApplicationTests;
import com.ps.wikicoordinator.entity.WikiEventEntity;
import com.ps.wikicoordinator.enums.EvenetName;
import com.ps.wikicoordinator.event.EventProducer;
import com.ps.wikicoordinator.pojo.EventNotification;
import com.ps.wikicoordinator.repository.WikiEventRepository;
import com.ps.wikicoordinator.testcomponent.TestEventConsumer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.time.LocalTime;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ImportEventControllerTest extends AbstractWikiCoordinatorApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEventConsumer testEventConsumer;

    @Autowired
    private WikiEventRepository wikiEventRepository;

    @Test
    void triggerImport() throws Exception {

        this.mockMvc.perform(post("/api/trigger/v1/import-request").contentType(APPLICATION_JSON)
                .content("{ \"filenameToExtract\": \"test\"}")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Request to Load file has been successfully made. Please check import-status to get latest update")));
        Thread.sleep(100);
        Assertions.assertEquals(1, testEventConsumer.getEventNotifications().size());
        Assertions.assertEquals("test", testEventConsumer.getEventNotifications().get(0).getFileName());
        Assertions.assertEquals(EvenetName.REQUEST_TO_START_READING_RESOURCE.getName(), testEventConsumer.getEventNotifications().get(0).getEventName());
    }

}