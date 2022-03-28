package com.nct.business.core.impl;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("nct.ninja-api")
@Data
public class APINinjaConfiguration {
    private String header;
    private String apiKey;
    private String url;
}
