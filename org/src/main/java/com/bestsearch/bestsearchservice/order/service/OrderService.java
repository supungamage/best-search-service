package com.bestsearch.bestsearchservice.order.service;

import com.bestsearch.bestsearchservice.order.dto.OrderInputDTO;
import com.bestsearch.bestsearchservice.order.dto.OrderOutputDTO;
import com.bestsearch.bestsearchservice.order.model.enums.Status;

import java.util.List;

public interface OrderService {

    OrderOutputDTO saveOrder(OrderInputDTO orderInputDTO);
    OrderOutputDTO getOrderById(long id);
    OrderOutputDTO getOrderByRef(String orderRef, long organizationTypeId);
    void changeOrderStatus(long id, Status toStatus);
    OrderOutputDTO updateOrder(long id, OrderInputDTO orderInputDTO);
    List<OrderOutputDTO> getOrders();
}
