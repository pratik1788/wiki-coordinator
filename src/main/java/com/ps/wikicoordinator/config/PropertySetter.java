package com.ps.wikicoordinator.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PropertySetter {
    @PostConstruct
    public void setProperty() {
        System.setProperty("datastax-java-driver.basic.request.timeout", "10 seconds");
    }
}
