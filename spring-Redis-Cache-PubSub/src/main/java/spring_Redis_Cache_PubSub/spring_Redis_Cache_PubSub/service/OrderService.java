package spring_Redis_Cache_PubSub.spring_Redis_Cache_PubSub.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import spring_Redis_Cache_PubSub.spring_Redis_Cache_PubSub.entity.Order;
import spring_Redis_Cache_PubSub.spring_Redis_Cache_PubSub.repository.OrderRepository;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

@Service
public class OrderService implements Serializable {

    @Autowired
    public OrderRepository orderRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    // Cache the created order only if the order ID is not null
    @CachePut(value = "orders", key = "#order.orderId", condition = "#order.orderId!=null")
    public String createOrder(Order order) {
        orderRepository.save(order);
        logger.info("Create Order method executed");
        return "Order Successfully Created";
    }

    // Cache all orders only if the result is not empty
    @Cacheable(value = "orders", unless = "#result == null || #result.isEmpty()")
    public List<Order> getAllOrders() {
        logger.info("GetAllOrders method executed");
        List<Order> orderList = (List<Order>) orderRepository.findAll();
        return orderList;
    }

    // Update the order cache only if the update operation is successful (result contains "updated")
    @CachePut(value = "orders", key = "order.orderId", unless = "!#result.contains('updated')")
    public String updateOrderDetails(Order order) {
        Order updatedOrder = new Order();
        updatedOrder.setOrderId(order.getOrderId());
        updatedOrder.setItemName(order.getItemName());
        updatedOrder.setAddress(order.getAddress());
        updatedOrder.setOrderdate(order.getOrderdate());
        updatedOrder.setOrderTime(order.getOrderTime());
        return "Successfully Updated";
    }

    // Remove the order from cache if deletion is successful
    @Caching(evict = {
            @CacheEvict(value = "orders", key = "#orderId"),
            @CacheEvict(value = "orders", allEntries = true)})
    public String deleteOrderById(String orderId) {
        orderRepository.deleteById(orderId);
        return "Sucessfully Deleted";
    }
}
