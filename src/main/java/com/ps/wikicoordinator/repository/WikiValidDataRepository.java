package com.ps.wikicoordinator.repository;


import com.ps.wikicoordinator.entity.WikiValidDataEntity;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface WikiValidDataRepository extends ReactiveCassandraRepository<WikiValidDataEntity,String> {
    @Query("select * from WIKI_VALID_DATA where DATA_MILESTONE =?0 and HOUR = ?1 PER PARTITION LIMIT ?2 ALLOW FILTERING")
    Flux<WikiValidDataEntity> getTopNQueryPerLanguageFromDataMilestoneAndHour(int dataMilestone, int hour, int n);

}

