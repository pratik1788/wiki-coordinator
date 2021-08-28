package com.ps.wikicoordinator.repository;


import com.ps.wikicoordinator.entity.WikiValidDataEntity;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikiValidDataRepository extends ReactiveCassandraRepository<WikiValidDataEntity,String> {

}

