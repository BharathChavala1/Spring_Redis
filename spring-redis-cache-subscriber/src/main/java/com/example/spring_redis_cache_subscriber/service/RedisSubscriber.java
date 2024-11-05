package com.example.spring_redis_cache_subscriber.service;

import com.example.spring_redis_cache_subscriber.entity.Order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class RedisSubscriber  implements MessageListener {

    private  RedisTemplate<String, Order> redisTemplate;

    @Autowired
    public RedisSubscriber(RedisTemplate<String, Order> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // Deserialize to Order using Jackson2JsonRedisSerializer
        Jackson2JsonRedisSerializer<Order> serializer = new Jackson2JsonRedisSerializer<>(Order.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        serializer.setObjectMapper(mapper);

        Order order = serializer.deserialize(message.getBody());
        System.out.println("Received order: " + order);
    }
}
