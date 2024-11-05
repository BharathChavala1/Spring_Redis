package spring_Redis_Cache_PubSub.spring_Redis_Cache_PubSub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_Redis_Cache_PubSub.spring_Redis_Cache_PubSub.entity.Order;
import spring_Redis_Cache_PubSub.spring_Redis_Cache_PubSub.service.OrderService;
import spring_Redis_Cache_PubSub.spring_Redis_Cache_PubSub.service.RedisPublisher;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    public OrderService orderService;

    @Autowired
    public RedisPublisher publisher;

    @ResponseBody
    @RequestMapping(path = "/save",method = RequestMethod.POST)
    public ResponseEntity<?> createOrder(@RequestBody Order order){
       String results =  orderService.createOrder(order);
       publisher.publishOrder(order);
        return new ResponseEntity<>(results, HttpStatus.ACCEPTED);
    }

    @ResponseBody
    @RequestMapping(path="/getOrders",method = RequestMethod.GET)
    public ResponseEntity<?> getOrders(){
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
    }

    @ResponseBody
    @RequestMapping(path="/updateOrderDetails",method = RequestMethod.PUT)
    public ResponseEntity<?> updateOrder(@RequestBody Order order){
        String results = orderService.updateOrderDetails(order);
        return new ResponseEntity<>(results,HttpStatus.ACCEPTED);
    }
    @ResponseBody
    @RequestMapping(path = "/deleteOrderById/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOrder(@PathVariable String id){
        String result = orderService.deleteOrderById(id);
        return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
    }
}
