package com.bestSearch.order.controller;

import com.bestSearch.order.dto.OrderInputDTO;
import com.bestSearch.order.dto.OrderOutputDTO;
import com.bestSearch.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderOutputDTO> addOrder(OrderInputDTO orderInputDTO) {
        return ResponseEntity.ok(this.orderService.saveOrder(orderInputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderOutputDTO> updateOrder(@PathVariable("id") long id, OrderInputDTO orderInputDTO) {
        return ResponseEntity.ok(this.orderService.updateOrder(id, orderInputDTO));
    }

    @GetMapping
    public ResponseEntity<List<OrderOutputDTO>> getOrders() {
        return ResponseEntity.ok(this.orderService.getOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderOutputDTO> getOrderById(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.orderService.getOrderById(id));
    }

    @GetMapping("/{ref}")
    public ResponseEntity<OrderOutputDTO> getOrderByRef(@PathVariable String orderRef, @RequestParam long organizationTypeId) {
        return ResponseEntity.ok(this.orderService.getOrderByRef(orderRef, organizationTypeId));
    }
}
