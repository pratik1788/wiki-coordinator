package com.ps.wikicoordinator.pojo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WikiDataResponse {
    String pageName;
    String language;
    int views;
}
