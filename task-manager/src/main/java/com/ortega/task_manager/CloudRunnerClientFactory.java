package com.ortega.task_manager;

import feign.Feign;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class CloudRunnerClientFactory {

    public CloudRunnerClient getClient(String url) {
        return Feign.builder()
                .encoder(new SpringEncoder(() -> new HttpMessageConverters(new MappingJackson2HttpMessageConverter())))
                .decoder(new SpringDecoder(() -> new HttpMessageConverters(new MappingJackson2HttpMessageConverter())))
                .target(CloudRunnerClient.class, url);
    }

}
