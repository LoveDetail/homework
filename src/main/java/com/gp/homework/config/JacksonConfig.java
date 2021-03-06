package com.gp.homework.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Create by Wayne on 2020/8/4
 */

//@Configuration
public class JacksonConfig {

//    @Bean
//    @Primary
//    @Qualifier("xml")
//    public XmlMapper xmlMapper(Jackson2ObjectMapperBuilder builder) {
//        XmlMapper mapper = builder.createXmlMapper(true).build();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        return mapper;
//    }

    @Bean
    @Qualifier("json")
    public ObjectMapper jsonMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper mapper = builder.createXmlMapper(false).build();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}
