package com.allianz.interview.rest.config;

import com.allianz.interview.webservice.config.WebserviceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Import(WebserviceConfiguration.class)
@Configuration
public class ApplicationConfig  implements WebMvcConfigurer {

}

