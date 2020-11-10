package com.bestSearch.order.dto;

import com.bestSearch.order.model.enums.OrderType;
import com.bestSearch.order.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDTO {
    private long userId;
    private OrderType orderType;
    private long organizationTypeId;
    private Double longitude;
    private Double latitude;
    private long organizationId;
}
