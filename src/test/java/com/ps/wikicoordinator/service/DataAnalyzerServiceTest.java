package com.ps.wikicoordinator.service;

import com.ps.wikicoordinator.entity.WikiValidDataEntity;
import com.ps.wikicoordinator.repository.WikiValidDataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class DataAnalyzerServiceTest {

    @Mock
    private WikiValidDataRepository validDataRepository;

    @InjectMocks
    private DataAnalyzerService dataAnalyzerService;

    @Test
    void getTopPagesByYearMonthDatHour_Top2() {

        Flux<WikiValidDataEntity> dbResponse= dataSetUp();
        Mockito.when(validDataRepository.getWikiDataByDataMilestoneAndHour(anyInt(),anyInt())).thenReturn(dbResponse);
        List<WikiValidDataEntity> resultList = dataAnalyzerService.getTopPagesByYearMonthDatHour(20120101,0,2).collectList().block();
        List<WikiValidDataEntity> expectedList=
                Arrays.asList(
                        WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("en").pageName("en1").nonUniqueViews(120).recordId(1).build(),
                        WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("en").pageName("en2").nonUniqueViews(100).recordId(3).build(),
                        WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("gu").pageName("gu1").nonUniqueViews(180).recordId(5).build(),
                        WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("gu").pageName("gu2").nonUniqueViews(70).recordId(6).build()
                );
        Assertions.assertEquals(4,resultList.size());
        Assertions.assertEquals(expectedList,resultList);

    }

    @Test
    void getPageCount_with_language() throws Exception{

        Mockito.when(validDataRepository.getPageCountByDataMilestoneAndHourAndLanguage(anyInt(),anyInt(),anyString())).thenReturn(Mono.just(100));
        Integer result = dataAnalyzerService.getPageCount(20120101,0,"en").block();
        Assertions.assertEquals(100,result);
    }

    @Test
    void getPageCount_without_language() throws Exception{
        Mockito.when(validDataRepository.getPageCountByDataMilestoneAndHour(anyInt(),anyInt())).thenReturn(Mono.just(100));
        Integer result = dataAnalyzerService.getPageCount(20120101,0,null).block();
        Assertions.assertEquals(100,result);
    }

    @Test
    void getViewCount_with_language() throws Exception{

        Mockito.when(validDataRepository.getTotalViewCountyByDataMilestoneAndHourAndLanguage(anyInt(),anyInt(),anyString())).thenReturn(Mono.just(100));
        Integer result = dataAnalyzerService.getViewCount(20120101,0,"en").block();
        Assertions.assertEquals(100,result);
    }

    @Test
    void getViewCount_without_language() throws Exception{
        Mockito.when(validDataRepository.getTotalViewCountByDataMilestoneAndHour(anyInt(),anyInt())).thenReturn(Mono.just(100));
        Integer result = dataAnalyzerService.getViewCount(20120101,0,null).block();
        Assertions.assertEquals(100,result);
    }

    @Test
    void getTopPagesByYearMonthDatHour_Top1() {

        Flux<WikiValidDataEntity> dbResponse= dataSetUp();
        Mockito.when(validDataRepository.getWikiDataByDataMilestoneAndHour(anyInt(),anyInt())).thenReturn(dbResponse);
        List<WikiValidDataEntity> resultList = dataAnalyzerService.getTopPagesByYearMonthDatHour(20120101,0,1).collectList().block();
        List<WikiValidDataEntity> expectedList=
                Arrays.asList(
                        WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("en").pageName("en1").nonUniqueViews(120).recordId(1).build(),
                        WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("gu").pageName("gu1").nonUniqueViews(180).recordId(5).build()
                );
        Assertions.assertEquals(2,resultList.size());
        Assertions.assertEquals(expectedList,resultList);

    }

    private Flux<WikiValidDataEntity> dataSetUp(){

        List<WikiValidDataEntity> validDataList=new ArrayList<>();
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("en").pageName("en1").nonUniqueViews(120).recordId(1).build());
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("en").pageName("en2").nonUniqueViews(100).recordId(3).build());
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("en").pageName("en3").nonUniqueViews(5).recordId(2).build());
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("gu").pageName("gu1").nonUniqueViews(180).recordId(5).build());
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("gu").pageName("gu2").nonUniqueViews(70).recordId(6).build());
        validDataList.add(WikiValidDataEntity.builder().dataMilestone(20120101).hour(0).language("gu").pageName("gu3").nonUniqueViews(3).recordId(4).build());
        return Flux.fromIterable(validDataList);
    }
}