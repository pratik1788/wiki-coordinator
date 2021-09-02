package com.ps.wikicoordinator.service;

import com.ps.wikicoordinator.entity.WikiValidDataEntity;
import com.ps.wikicoordinator.repository.WikiValidDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class DataAnalyzerService {

    @Autowired
    private WikiValidDataRepository wikiValidDataRepository;

    public Mono<Integer> getPageCount(int yearMonthDay, int hour, String language) throws IOException {
        return language==null ? wikiValidDataRepository.getPageCountByDataMilestoneAndHour(yearMonthDay,hour):
                wikiValidDataRepository.getPageCountByDataMilestoneAndHourAndLanguage(yearMonthDay,hour,language);
    }

    public Mono<Integer> getViewCount(int yearMonthDay, int hour, String language) throws IOException {
        return language==null ? wikiValidDataRepository.getTotalViewCountByDataMilestoneAndHour(yearMonthDay,hour):
                wikiValidDataRepository.getTotalViewCountyByDataMilestoneAndHourAndLanguage(yearMonthDay,hour,language);
    }

    public Flux<WikiValidDataEntity> getTopPagesByYearMonthDatHour(int yearMonthDay,int hour, int topN){
        var wrapperCount = new Object(){ int count = 0; };
        var wrapperLanguage = new Object(){ String language = "-"; };

        return wikiValidDataRepository.getWikiDataByDataMilestoneAndHour(yearMonthDay,hour).filter(e -> {
            if(wrapperCount.count < topN &&  ( wrapperLanguage.language.equals(e.getLanguage()) ||  wrapperLanguage.language.equals("-") )){
                wrapperCount.count++;
                wrapperLanguage.language=e.getLanguage();
                return true;
            }else if(!wrapperLanguage.language.equals(e.getLanguage())){
                wrapperCount.count=1;
                wrapperLanguage.language=e.getLanguage();
                return true;
            }
            else{
                return false;
            }
        });
    }
}
