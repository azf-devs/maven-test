package com.allianz.maventest.rest.config;

import com.allianz.maventest.webservice.provider.CompanyProvider;
import com.allianz.maventest.webservice.service.CompanyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeans {

    @Bean
    CompanyService companyService(CompanyProvider companyProvider) {
        return new CompanyService(companyProvider);
    }
}
