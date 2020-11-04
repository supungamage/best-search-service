package com.bestsearch.bestsearchservice.order.model;

import com.bestsearch.bestsearchservice.common.audit.Auditable;
import com.bestsearch.bestsearchservice.order.dto.OrderOutputDTO;
import com.bestsearch.bestsearchservice.order.model.enums.OrderStatus;
import com.bestsearch.bestsearchservice.order.model.enums.OrderType;
import com.bestsearch.bestsearchservice.organization.dto.OrganizationOutputDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "order")
@Getter
@SuperBuilder
@NoArgsConstructor
public class Order extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq" ,allocationSize = 1)
    private long id;

    private String orderRef;
    private long userId;
    private OrderType orderType;
    private OrderStatus orderStatus;
    private long organizationTypeId;

    @NonNull
    private Double longitude;

    @NonNull
    private Double latitude;

    private long organizationId;

    @JsonIgnore
    public OrderOutputDTO viewAsOrderOutputDTO() {
        return OrderOutputDTO.builder().id(id).orderRef(orderRef).userId(userId).orderType(orderType)
                .orderStatus(orderStatus).latitude(latitude).longitude(longitude)
                .organizationTypeId(organizationTypeId)
                .organization(null)
                .build();
    }
}
