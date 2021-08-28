package com.ps.wikicoordinator.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Builder
@Table("WIKI_VALID_DATA")
public class WikiValidDataEntity {

    @PrimaryKeyColumn(name = "DATA_MILESTONE", ordinal = 0, type =
            PrimaryKeyType.PARTITIONED)
    private int dataMilestone;
    @PrimaryKeyColumn(name = "HOUR", ordinal = 0, type =
            PrimaryKeyType.PARTITIONED)
    private int hour;
    @PrimaryKeyColumn(name = "LANGUAGE", ordinal = 0, type =
            PrimaryKeyType.PARTITIONED)
    private String language;
    @PrimaryKeyColumn(name = "NON_UNIQUE_VIEWS", ordinal = 0, type =
            PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Integer nonUniqueViews;

    @PrimaryKeyColumn(name = "PAGE_NAME", ordinal = 0, type =
            PrimaryKeyType.CLUSTERED)
    private String pageName;

    @Column("BYTE_TRANSFERRED")
    private Long byteTransferred;
}
