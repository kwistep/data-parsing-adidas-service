package com.apparelshop.dataparsingadidasservice;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@SpringBootApplication
@EnableFeignClients("com.appareldiving.dataparsingadidasservice")
@EnableEurekaClient
public class DataParsingAdidasServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataParsingAdidasServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(15000);
        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(15000);

        return restTemplate;
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.getPoolConfig().setMaxIdle(30);
        jedisConnectionFactory.getPoolConfig().setMinIdle(10);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return template;
    }

    @Bean
    public Sampler defaultSampler()
    {
        return Sampler.ALWAYS_SAMPLE;
    }
}
