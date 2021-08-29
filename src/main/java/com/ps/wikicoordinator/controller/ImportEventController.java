package com.ps.wikicoordinator.controller;



import com.ps.wikicoordinator.entity.WikiEventEntity;
import com.ps.wikicoordinator.enums.EvenetName;
import com.ps.wikicoordinator.event.EventProducer;
import com.ps.wikicoordinator.pojo.DataImportRequest;
import com.ps.wikicoordinator.pojo.EventNotification;
import com.ps.wikicoordinator.repository.WikiEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController("/api/trigger/v1")
public class ImportEventController {

    @Autowired
    private EventProducer producer;

    @Autowired
    private WikiEventRepository wikiEventRepository;

    @RequestMapping(value = "/import-request", method = RequestMethod.POST)
    public String extract(@RequestBody DataImportRequest dataImportRequest) throws IOException {
        producer.sendMessage(EventNotification.newBuilder().setFileName(dataImportRequest.getFilenameToExtract())
        .setEventName(EvenetName.REQUEST_TO_START_READING_RESOURCE.getName()).build());
        return "Request to Load file has been successfully made. Please check import-status to get latest update";
    }

    @RequestMapping(value = "/import-status/{fileName}", method = RequestMethod.GET)
    public Flux<WikiEventEntity> getEvents(@PathVariable String fileName) throws IOException {
        return wikiEventRepository.findByFileName(Mono.just(fileName));
    }
}
