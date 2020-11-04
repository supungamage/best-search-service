package com.bestsearch.bestsearchservice.order.dto;

import com.bestsearch.bestsearchservice.order.model.enums.OrderStatus;
import com.bestsearch.bestsearchservice.order.model.enums.OrderType;
import com.bestsearch.bestsearchservice.organization.dto.OrganizationOutputDTO;
import com.bestsearch.bestsearchservice.organization.model.Organization;
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
    private OrderStatus orderStatus;
    private long organizationTypeId;
    private Double longitude;
    private Double latitude;
    private OrganizationOutputDTO organization;
}
