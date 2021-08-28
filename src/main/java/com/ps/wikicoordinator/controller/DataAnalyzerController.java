package com.ps.wikicoordinator.controller;



import com.ps.wikicoordinator.entity.WikiEventEntity;
import com.ps.wikicoordinator.enums.EvenetName;
import com.ps.wikicoordinator.event.EventProducer;
import com.ps.wikicoordinator.pojo.DataImportRequest;
import com.ps.wikicoordinator.pojo.EventNotification;
import com.ps.wikicoordinator.repository.WikiEventRepository;
import com.ps.wikicoordinator.repository.WikiValidDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController("/api/v1")
public class DataAnalyzerController {


    @Autowired
    private WikiValidDataRepository wikiValidDataRepository;
}
