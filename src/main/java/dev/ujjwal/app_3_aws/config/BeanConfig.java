package dev.ujjwal.app_3_aws.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
