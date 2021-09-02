package com.ps.wikicoordinator.repository;


import com.ps.wikicoordinator.entity.WikiValidDataEntity;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface WikiValidDataRepository extends ReactiveCassandraRepository<WikiValidDataEntity,String> {
    @Query("select * from WIKI_VALID_DATA where DATA_MILESTONE =?0 and HOUR = ?1 PER PARTITION LIMIT ?2 ALLOW FILTERING")
    Flux<WikiValidDataEntity> getTopNQueryPerLanguageFromDataMilestoneAndHour(int dataMilestone, int hour, int n);

    @Query("select count(1) from WIKI_VALID_DATA where DATA_MILESTONE =?0 and HOUR = ?1 and LANGUAGE = ?2 ")
    Mono<Integer> getPageCountByDataMilestoneAndHourAndLanguage(int dataMilestone, int hour, String language);

    @Query("select count(1) from WIKI_VALID_DATA where DATA_MILESTONE =?0 and HOUR = ?1 ALLOW FILTERING")
    Mono<Integer> getPageCountByDataMilestoneAndHour(int dataMilestone, int hour );

    @Query("select sum(NON_UNIQUE_VIEWS) from WIKI_VALID_DATA where DATA_MILESTONE =?0 and HOUR = ?1 ALLOW FILTERING")
    Mono<Integer> getTotalViewCountByDataMilestoneAndHour(int dataMilestone, int hour );

    @Query("select sum(NON_UNIQUE_VIEWS) from WIKI_VALID_DATA where DATA_MILESTONE =?0 and HOUR = ?1 and LANGUAGE = ?2 ")
    Mono<Integer> getTotalViewCountyByDataMilestoneAndHourAndLanguage(int dataMilestone, int hour, String language );

    @Query("select * from WIKI_VALID_DATA where DATA_MILESTONE =?0 and HOUR = ?1 ALLOW FILTERING")
    Flux<WikiValidDataEntity> getWikiDataByDataMilestoneAndHour(int dataMilestone, int hour );

}

