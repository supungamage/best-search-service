package com.bestSearch.order.dto;

import com.bestSearch.order.model.enums.OrderType;
import com.bestSearch.order.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OrderOutputDTO {
    private long id;
    private String orderRef;
    private long userId;
    private OrderType orderType;
    private Status status;
    private long organizationTypeId;
    private Double longitude;
    private Double latitude;
    private long organizationId;
}
