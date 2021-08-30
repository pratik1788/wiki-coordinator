package com.ps.wikicoordinator.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalTime;

@Data
@Builder
@EqualsAndHashCode
@Table("WIKI_EVENT_DATA")
public class WikiEventEntity {

    @PrimaryKeyColumn(name = "FILE_NAME", ordinal = 0, type =
            PrimaryKeyType.PARTITIONED)
    private String fileName;
    @PrimaryKeyColumn(name = "EVENT_TIME", ordinal = 0, type =
            PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private LocalTime eventTime;
    @Column("EVENT_NAME")
    private String eventName;
    @Column("DETAILS")
    private String details;
}
