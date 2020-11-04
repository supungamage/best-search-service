package com.bestsearch.bestsearchservice.order.dto;

import com.bestsearch.bestsearchservice.order.model.enums.OrderStatus;
import com.bestsearch.bestsearchservice.order.model.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInputDTO {

    private String orderRef;
    private long userId;
    private OrderType orderType;
    private OrderStatus orderStatus;
    private long organizationTypeId;
    private Double longitude;
    private Double latitude;
    private long organizationId;
}
