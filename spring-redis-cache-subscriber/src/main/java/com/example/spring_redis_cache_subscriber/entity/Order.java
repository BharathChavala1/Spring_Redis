package com.example.spring_redis_cache_subscriber.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Order")
public class Order implements Serializable {
    @Id
    private String orderId;
    private String itemName;
    private String address;
    private LocalDate orderdate;
    private LocalTime orderTime;
}
