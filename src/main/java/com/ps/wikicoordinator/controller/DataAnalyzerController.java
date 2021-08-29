package com.ps.wikicoordinator.controller;



import com.ps.wikicoordinator.entity.WikiValidDataEntity;
import com.ps.wikicoordinator.repository.WikiValidDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;

@RestController("/api/data-analyze/v1")
public class DataAnalyzerController {

    @Autowired
    private WikiValidDataRepository wikiValidDataRepository;

    @RequestMapping(value = "/getTop10byLanguage/{yearMonthDay}/{hour}}", method = RequestMethod.GET)
    public Flux<WikiValidDataEntity> getEvents(@PathVariable int yearMonthDay, @PathVariable int hour) throws IOException {
        return wikiValidDataRepository.getTopNQueryPerLanguageFromDataMilestoneAndHour(yearMonthDay,hour,10);
    }
}
