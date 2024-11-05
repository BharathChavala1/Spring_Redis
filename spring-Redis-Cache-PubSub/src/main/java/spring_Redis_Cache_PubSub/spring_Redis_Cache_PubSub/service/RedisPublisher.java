package spring_Redis_Cache_PubSub.spring_Redis_Cache_PubSub.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import spring_Redis_Cache_PubSub.spring_Redis_Cache_PubSub.entity.Order;

@Service
public class RedisPublisher {

    private final RedisTemplate<String, Order> redisTemplate;
    private final ChannelTopic channelTopic;

    @Autowired
    public RedisPublisher(RedisTemplate redisTemplate, ChannelTopic channelTopic) {
        this.redisTemplate = redisTemplate;
        this.channelTopic = new ChannelTopic("OrderTopic");
    }

    public void publishOrder(Order order){
        redisTemplate.convertAndSend(channelTopic.getTopic(),order);
    }
}
