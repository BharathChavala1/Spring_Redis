package spring_Redis_Cache_PubSub.spring_Redis_Cache_PubSub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring_Redis_Cache_PubSub.spring_Redis_Cache_PubSub.entity.Order;


@Repository
public interface OrderRepository extends CrudRepository<Order, String> {
}
