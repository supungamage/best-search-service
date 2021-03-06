package com.bestSearch.order.service;

import com.bestSearch.order.dto.OrderCreateDTO;
import com.bestSearch.order.dto.OrderInputDTO;
import com.bestSearch.order.dto.OrderOutputDTO;
import com.bestSearch.order.model.enums.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OrderService {

    OrderOutputDTO saveOrder(OrderCreateDTO orderCreateDTO);
    OrderOutputDTO getOrderById(long id);
    OrderOutputDTO getOrderByRef(String orderRef, long organizationTypeId);
    void changeOrderStatus(long id, Status toStatus);
    OrderOutputDTO updateOrder(long id, OrderInputDTO orderInputDTO);
    List<OrderOutputDTO> getOrders();
    Map<LocalDate, List<OrderOutputDTO>> getCurrentOrders(long orgTypeId, long userId);
    Map<LocalDate, List<OrderOutputDTO>> getPastOrders(long orgTypeId, long userId);
}
