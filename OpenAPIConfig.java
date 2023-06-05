package com.practice.chatgpt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAPIConfig {

    @Value("openai.key")
    private String openaiApiKey;

    @Bean
public RestTemplate template(){

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) ->{
                request.getHeaders().add("Authorization","Bearer "+openaiApiKey);
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return execution.execute(request,body);
        });

    return restTemplate;
}

}
