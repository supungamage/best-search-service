package com.bestSearch.order.service;

import com.bestSearch.order.dto.OrderInputDTO;
import com.bestSearch.order.dto.OrderOutputDTO;
import com.bestSearch.order.model.enums.Status;

import java.util.List;

public interface OrderService {

    OrderOutputDTO saveOrder(OrderInputDTO orderInputDTO);
    OrderOutputDTO getOrderById(long id);
    OrderOutputDTO getOrderByRef(String orderRef, long organizationTypeId);
    void changeOrderStatus(long id, Status toStatus);
    OrderOutputDTO updateOrder(long id, OrderInputDTO orderInputDTO);
    List<OrderOutputDTO> getOrders();
}
