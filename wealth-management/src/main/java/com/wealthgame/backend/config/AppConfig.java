package com.wealthgame.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
    //@Value("${application.name}")
    private final AppProperties appPropertie;

    public AppConfig(AppProperties appPropertie) {
        this.appPropertie = appPropertie;
    }

    public void printAppName()
    {
        System.out.println(appPropertie.getName());
        System.out.println(appPropertie.getEnvironment());
        System.out.println(appPropertie.getSupportEmail());
        System.out.println(appPropertie.getVersion());
    }
}
