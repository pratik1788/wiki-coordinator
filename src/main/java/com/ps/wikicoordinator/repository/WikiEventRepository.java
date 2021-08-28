package com.ps.wikicoordinator.repository;

import com.ps.wikicoordinator.entity.WikiEventEntity;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface WikiEventRepository extends ReactiveCassandraRepository<WikiEventEntity,String> {
    Flux<WikiEventEntity> findByFileName(Mono<String> fileName);
}
