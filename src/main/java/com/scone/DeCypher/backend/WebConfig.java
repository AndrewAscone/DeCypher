package com.scone.DeCypher.backend;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Value("${cors.allowed-origins}")
    private String allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("POST")
                .allowedHeaders("*");

        registry.addMapping("/file/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("POST")
                .allowedHeaders("*");
    }

    @PostConstruct
    public void logCorsSetup() {
        logger.info("CORS Configuration initialized. Allowed origins: {}", (Object) allowedOrigins);
    }
}
