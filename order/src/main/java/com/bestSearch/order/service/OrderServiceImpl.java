package com.bestSearch.order.service;

import com.bestSearch.order.utils.IdentifierGenerator;
import com.bestSearch.share.exception.ResourceNotFoundException;
import com.bestSearch.order.dto.OrderInputDTO;
import com.bestSearch.order.dto.OrderOutputDTO;
import com.bestSearch.order.model.Order;
import com.bestSearch.order.model.enums.Status;
import com.bestSearch.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final String YEAR_MONTH_KEY = "YEARMONTH";
    private static final String ORDERID = "ORDID";
    private static final String YEAR_MONTH_DATE_PATTERN = "ddMMyy";
    private final String orderRefPattern;

    private final OrderRepository orderRepository;



    public OrderServiceImpl(final OrderRepository orderRepository, final @Value("${order.ref.pattern}") String orderRefPattern) {
        this.orderRepository = orderRepository;
        this.orderRefPattern = orderRefPattern;
    }

    @Override
    public OrderOutputDTO saveOrder(OrderInputDTO orderInputDTO) {
        long id = orderRepository.getNextId();
        String orderRef = IdentifierGenerator.generateIdentifier(
                Map.of(ORDERID, id, YEAR_MONTH_KEY,
                        LocalDate.now().format(DateTimeFormatter.ofPattern(YEAR_MONTH_DATE_PATTERN))),
                orderRefPattern);

        Order toBeSaved = Order.builder()
                .id(id)
                .latitude(orderInputDTO.getLatitude())
                .longitude(orderInputDTO.getLongitude())
                .orderRef(orderRef)
                .status(orderInputDTO.getStatus())
                .orderType(orderInputDTO.getOrderType())
                .organizationId(orderInputDTO.getOrganizationId())
                .organizationTypeId(orderInputDTO.getOrganizationTypeId())
                .userId(orderInputDTO.getUserId())
                .build();

        return orderRepository.save(toBeSaved).viewAsOrderOutputDTO();
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

    @Override
    public Map<LocalDate, List<OrderOutputDTO>> getCurrentOrders(long orgTypeId, long userId) {
        return orderRepository.getCurrentOrders(orgTypeId, userId, Status.CLOSED)
                .orElseThrow(() -> new ResourceNotFoundException("No data found"))
                .stream().map(Order::viewAsOrderOutputDTO)
                .collect(Collectors.groupingBy(OrderOutputDTO::getOrderDate));
    }

    @Override
    public Map<LocalDate, List<OrderOutputDTO>> getPastOrders(long orgTypeId, long userId) {
        return orderRepository.getPastOrders(orgTypeId, userId, Status.CLOSED)
                .orElseThrow(() -> new ResourceNotFoundException("No data found"))
                .stream().map(Order::viewAsOrderOutputDTO)
                .collect(Collectors.groupingBy(OrderOutputDTO::getOrderDate));
    }
}
