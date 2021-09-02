package com.ps.wikicoordinator.controller;



import com.ps.wikicoordinator.entity.WikiValidDataEntity;
import com.ps.wikicoordinator.repository.WikiValidDataRepository;
import com.ps.wikicoordinator.service.DataAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController("/api/data-analyze/v1")
public class DataAnalyzerController {

    @Autowired
    private DataAnalyzerService dataAnalyzerService;

/*    @RequestMapping(value = "/getTop10PagesByLanguage/{yearMonthDay}/{hour}}", method = RequestMethod.GET)
    public Flux<WikiValidDataEntity> getTop10Pages(@PathVariable int yearMonthDay, @PathVariable int hour) throws IOException {
        return wikiValidDataRepository.getTopNQueryPerLanguageFromDataMilestoneAndHour(yearMonthDay,hour,10);
    }

    @RequestMapping(value = "/getTopNPagesByLanguage/{yearMonthDay}/{hour}/{n}}", method = RequestMethod.GET)
    public Flux<WikiValidDataEntity> getTopNPages(@PathVariable int yearMonthDay, @PathVariable int hour, @PathVariable int n) throws IOException {
        return wikiValidDataRepository.getTopNQueryPerLanguageFromDataMilestoneAndHour(yearMonthDay,hour,n);
    }
*/
    @RequestMapping(value = "/getTotalPages/{yearMonthDay}/{hour}/{language}}", method = RequestMethod.GET)
    public Mono<Integer> getPageCount(@PathVariable int yearMonthDay, @PathVariable int hour, @PathVariable(required = false) String language) throws IOException {
        return dataAnalyzerService.getPageCount(yearMonthDay,hour,language);
    }

    @RequestMapping(value = "/getTotalViews/{yearMonthDay}/{hour}/{language}}", method = RequestMethod.GET)
    public Mono<Integer> getViewCount(@PathVariable int yearMonthDay, @PathVariable int hour, @PathVariable(required = false) String language) throws IOException {
        return dataAnalyzerService.getPageCount(yearMonthDay,hour,language);
    }

    @RequestMapping(value = "/getTop10PagesByLanguage/{yearMonthDay}/{hour}}", method = RequestMethod.GET)
    public Flux<WikiValidDataEntity> getTop10Pages(@PathVariable int yearMonthDay, @PathVariable int hour) throws IOException {
        return dataAnalyzerService.getTopPagesByYearMonthDatHour(yearMonthDay,hour,10);
    }

    @RequestMapping(value = "/getTopNPagesByLanguage/{yearMonthDay}/{hour}/{n}}", method = RequestMethod.GET)
    public Flux<WikiValidDataEntity> getTopNPages(@PathVariable int yearMonthDay, @PathVariable int hour, @PathVariable int n) throws IOException {
        return dataAnalyzerService.getTopPagesByYearMonthDatHour(yearMonthDay,hour,n);
    }
}
