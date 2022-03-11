package com.iamnoah1.influx.demo;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class InfluxClient {

    @Value("${influx.url}")
    String url;

    @Value("${influx.token}")
    String token;

    @Value("${influx.org}")
    String org;

    @Value("${influx.bucket}")
    String bucket;

    @Bean
    public InfluxDBClient getClient (){
        return InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
    }
}
