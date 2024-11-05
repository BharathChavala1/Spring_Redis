package spring_Redis_Cache_PubSub.spring_Redis_Cache_PubSub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringRedisCachePubSubApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRedisCachePubSubApplication.class, args);
	}

}
