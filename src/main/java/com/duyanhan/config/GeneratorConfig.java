package com.duyanhan.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = GeneratorProperties.class)
public class GeneratorConfig {

}
