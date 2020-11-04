package com.bestsearch.bestsearchservice.order.service;

import com.bestsearch.bestsearchservice.order.dto.OrderInputDTO;
import com.bestsearch.bestsearchservice.order.dto.OrderOutputDTO;
import com.bestsearch.bestsearchservice.order.model.Order;
import com.bestsearch.bestsearchservice.order.model.enums.OrderStatus;

public interface OrderService {

    OrderOutputDTO saveOrder(OrderInputDTO orderInputDTO);
    OrderOutputDTO getOrderById(long id);
    OrderOutputDTO getOrderByRef(String orderRef, long organizationTypeId);
    void changeOrderStatus(long id, OrderStatus toStatus);
    OrderOutputDTO updateOrder(long id, OrderInputDTO orderInputDTO);
}
