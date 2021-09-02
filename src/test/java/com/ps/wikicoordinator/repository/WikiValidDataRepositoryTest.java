package com.ps.wikicoordinator.repository;

import com.ps.wikicoordinator.AbstractWikiCoordinatorApplicationTests;
import com.ps.wikicoordinator.entity.WikiEventEntity;
import com.ps.wikicoordinator.entity.WikiValidDataEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WikiValidDataRepositoryTest extends AbstractWikiCoordinatorApplicationTests {

    @Autowired
    private WikiValidDataRepository wikiValidDataRepository;

    @BeforeEach
    public void setup(){
        List<WikiValidDataEntity> validDataList=new ArrayList<>();
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("en").pageName("en1").nonUniqueViews(120).recordId(1).build());
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("en").pageName("en3").nonUniqueViews(8).recordId(2).build());
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("en").pageName("en2").nonUniqueViews(100).recordId(3).build());
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("gu").pageName("gu3").nonUniqueViews(3).recordId(4).build());
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("gu").pageName("gu1").nonUniqueViews(180).recordId(5).build());
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("gu").pageName("gu2").nonUniqueViews(70).recordId(6).build());
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120102).hour(1).language("zu").pageName("zu1").nonUniqueViews(120).recordId(1).build());
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120102).hour(1).language("zu").pageName("zu3").nonUniqueViews(8).recordId(2).build());
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120102).hour(1).language("zu").pageName("zu2").nonUniqueViews(100).recordId(3).build());
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120102).hour(1).language("ch").pageName("ch3").nonUniqueViews(3).recordId(4).build());
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120102).hour(1).language("ch").pageName("ch1").nonUniqueViews(180).recordId(5).build());
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120102).hour(1).language("ch").pageName("ch2").nonUniqueViews(70).recordId(6).build());
        wikiValidDataRepository.saveAll(validDataList).blockLast();
    }

    @Test
    void getWikiDataByDataMilestoneAndHour(){
        List<WikiValidDataEntity> resultList= wikiValidDataRepository
                .getWikiDataByDataMilestoneAndHour(20120101,0).collectList().block();
        List<WikiValidDataEntity> expectedList=
                Arrays.asList(
                        WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("gu").pageName("gu1").nonUniqueViews(180).recordId(5).build(),
                        WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("gu").pageName("gu2").nonUniqueViews(70).recordId(6).build(),
                        WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("gu").pageName("gu3").nonUniqueViews(3).recordId(4).build(),
                        WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("en").pageName("en1").nonUniqueViews(120).recordId(1).build(),
                        WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("en").pageName("en2").nonUniqueViews(100).recordId(3).build(),
                        WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("en").pageName("en3").nonUniqueViews(8).recordId(2).build()
             );
        Assertions.assertEquals(6,resultList.size());
        Assertions.assertEquals(expectedList,resultList);
    }

    @Test
    void getPageCountByDataMilestoneAndHourAndLanguage(){

        Assertions.assertEquals(3,wikiValidDataRepository
                .getPageCountByDataMilestoneAndHourAndLanguage(20120101,0,"en").block());
    }

    @Test
    void getPageCountByDataMilestoneAndHour(){
        Assertions.assertEquals(6,wikiValidDataRepository
                .getPageCountByDataMilestoneAndHour(20120102,1).block());
    }


    @Test
    void getPageViewByDataMilestoneAndHourAndLanguage(){

        Assertions.assertEquals(228,wikiValidDataRepository
                .getTotalViewCountyByDataMilestoneAndHourAndLanguage(20120101,0,"en").block());
    }

    @Test
    void getPageViewByDataMilestoneAndHour(){
        Assertions.assertEquals(481,wikiValidDataRepository
                .getTotalViewCountByDataMilestoneAndHour(20120101,0).block());
    }
    @Test
    void getTopNQueryPerLanguageFromDataMilestoneAndHour_Top2() {
        List<WikiValidDataEntity> resultList= wikiValidDataRepository
                .getTopNQueryPerLanguageFromDataMilestoneAndHour(20120101,0,2).collectList().block();
        List<WikiValidDataEntity> expectedList=
                Arrays.asList(
                        WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("gu").pageName("gu1").nonUniqueViews(180).recordId(5).build(),
                        WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("gu").pageName("gu2").nonUniqueViews(70).recordId(6).build(),
                        WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("en").pageName("en1").nonUniqueViews(120).recordId(1).build(),
                        WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("en").pageName("en2").nonUniqueViews(100).recordId(3).build()
                );
        Assertions.assertEquals(4,resultList.size());
        Assertions.assertEquals(expectedList,resultList);

    }

    @Test
    void getTopNQueryPerLanguageFromDataMilestoneAndHour_Top1() {
        List<WikiValidDataEntity> resultList= wikiValidDataRepository
                .getTopNQueryPerLanguageFromDataMilestoneAndHour(20120102,1,1).collectList().block();
        List<WikiValidDataEntity> expectedList=
                Arrays.asList(
                        WikiValidDataEntity.builder().dataMilestone(20120102).hour(1).language("zu").pageName("zu1").nonUniqueViews(120).recordId(1).build(),
                        WikiValidDataEntity.builder().dataMilestone(20120102).hour(1).language("ch").pageName("ch1").nonUniqueViews(180).recordId(5).build()
                );
        Assertions.assertEquals(2,resultList.size());
        Assertions.assertEquals(expectedList,resultList);
    }


}