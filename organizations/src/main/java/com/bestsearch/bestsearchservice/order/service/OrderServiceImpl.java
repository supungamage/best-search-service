package com.bestsearch.bestsearchservice.order.service;

import com.bestsearch.bestsearchservice.common.exception.ResourceNotFoundException;
import com.bestsearch.bestsearchservice.order.dto.OrderInputDTO;
import com.bestsearch.bestsearchservice.order.dto.OrderOutputDTO;
import com.bestsearch.bestsearchservice.order.model.Order;
import com.bestsearch.bestsearchservice.order.model.enums.Status;
import com.bestsearch.bestsearchservice.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderOutputDTO saveOrder(OrderInputDTO orderInputDTO) {
        return orderRepository.save(Order.builder()
                .latitude(orderInputDTO.getLatitude())
                .longitude(orderInputDTO.getLongitude())
                .orderRef("AA")
                .status(orderInputDTO.getStatus())
                .orderType(orderInputDTO.getOrderType())
                .organizationId(orderInputDTO.getOrganizationId())
                .organizationTypeId(orderInputDTO.getOrganizationTypeId())
                .userId(orderInputDTO.getUserId())
                .build()).viewAsOrderOutputDTO();
    }

    @Override
    public OrderOutputDTO getOrderById(long id) {
        return orderRepository.findById(id)
                .map(Order::viewAsOrderOutputDTO)
                .orElseThrow(() -> new ResourceNotFoundException("No order found"));
    }

    @Override
    public OrderOutputDTO getOrderByRef(String orderRef, long organizationTypeId) {
        return orderRepository.findByOrderRefAndOrganizationTypeId(orderRef, organizationTypeId)
                .map(Order::viewAsOrderOutputDTO)
                .orElseThrow(() -> new ResourceNotFoundException("No order found"));
    }

    @Override
    public void changeOrderStatus(long id, Status toStatus) {
        orderRepository.updateOrderStatus(id, toStatus);
    }

    @Override
    public OrderOutputDTO updateOrder(long id, OrderInputDTO orderInputDTO) {
        return orderRepository.save(Order.builder()
                .id(id)
                .latitude(orderInputDTO.getLatitude())
                .longitude(orderInputDTO.getLongitude())
                .orderRef("")
                .status(orderInputDTO.getStatus())
                .orderType(orderInputDTO.getOrderType())
                .organizationId(orderInputDTO.getOrganizationId())
                .organizationTypeId(orderInputDTO.getOrganizationTypeId())
                .userId(orderInputDTO.getUserId())
                .build()).viewAsOrderOutputDTO();
    }

    @Override
    public List<OrderOutputDTO> getOrders() {
        return orderRepository.findAll().stream()
                .map(Order::viewAsOrderOutputDTO).collect(Collectors.toList());
    }
}
