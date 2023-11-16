package com.example.Neo_store111.service;

import com.example.Neo_store111.dto.OrderDetailsDto;
import com.example.Neo_store111.dto.OrderDto;
import com.example.Neo_store111.dto.OrderInfo;
import com.example.Neo_store111.model.Order;
import com.example.Neo_store111.model.OrderDetails;
import com.example.Neo_store111.model.Product;
import com.example.Neo_store111.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;

    public List<OrderInfo> allOrders() {
        return mapOrderToOrderINFO(orderRepository.findAll());
    }

    public ResponseEntity<Object> createOrder(OrderDto orderDto) {
        ResultMod resultMod = new ResultMod();
        List<OrderDetails> orderDetails = parseOrderDTOtoOrderDetails(orderDto.getDetails());
        double totalSum = 0.0;

        for (OrderDetails details : orderDetails) {
            totalSum += details.getTotalPrice();
        }

        Order order = Order.builder()
                .details(orderDetails)
                .totalPrice(totalSum)
                .address(orderDto.getAddress())
                .methodOfPurchases(orderDto.getMethodOfPurchases())
                .user(userService.checkUserOnExistAndReturn(orderDto.getUserId()))
                .build();

        orderRepository.save(order);
        resultMod.setResult("Order was created!");

        return ResponseEntity.ok(resultMod.getResult());
    }

    public ResponseEntity<Object> deleteOrder(Long orderId) {
        ResultMod resultMod = new ResultMod();

        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            orderRepository.delete(order);
            resultMod.setResult("Order with ID " + orderId + " was deleted!");

            return ResponseEntity.ok(resultMod.getResult());
        } else {
            resultMod.setResult("Order with ID " + orderId + " not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultMod.getResult());
        }
    }


    public List<OrderDetails> parseOrderDTOtoOrderDetails(List<OrderDetailsDto> orderDtos) {
        List<OrderDetails> orderDetails = new ArrayList<>();

        for (OrderDetailsDto details : orderDtos) {
            Product product = productService.checkProductOnExistAndReturn(details.getProductId());
            orderDetails.add(OrderDetails.builder()
                    .product(product)
                    .totalQuantity(productService.getAllQuantity())
                    /*-*/.totalPrice(product.getQuantity() * product.getProductPrice())
                    .build());
        }

        return orderDetails;
    }

    public List<OrderInfo> mapOrderToOrderINFO(List<Order> orders) {
        List<OrderInfo> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            orderDtos.add(OrderInfo.builder()
                    .userId(order.getUser().getUserId())
                    .address(order.getAddress())
                    .totalPrice(order.getTotalPrice())
                    .methodOfPurchases(order.getMethodOfPurchases())
                    .build());
        }

        return orderDtos;
    }
}
