package com.bestSearch.order.model;

import com.bestSearch.share.audit.Auditable;
import com.bestSearch.order.dto.OrderOutputDTO;
import com.bestSearch.order.model.enums.OrderType;
import com.bestSearch.order.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "orders")
@Getter
@SuperBuilder
@NoArgsConstructor
public class Order extends Auditable<String> {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="order_seq")
    //@SequenceGenerator(name = "order_seq", sequenceName = "order_seq" ,allocationSize = 1)
    private long id;

    private String orderRef;
    private long userId;

    @Enumerated(EnumType.ORDINAL)
    private OrderType orderType;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private long organizationTypeId;

    @NonNull
    private Double longitude;

    @NonNull
    private Double latitude;

    private long organizationId;

    private LocalDateTime orderedAt;

    @JsonIgnore
    public OrderOutputDTO viewAsOrderOutputDTO() {
        return OrderOutputDTO.builder().id(id).orderRef(orderRef).userId(userId).orderType(orderType)
                .status(status).latitude(latitude).longitude(longitude)
                .organizationTypeId(organizationTypeId)
                .organizationId(organizationId)
                .orderedAt(orderedAt)
                .period(ChronoUnit.HOURS.between(orderedAt, LocalDateTime.now()) < 24
                        ? ChronoUnit.HOURS.between(orderedAt, LocalDateTime.now())
                        : -1)
                .build();
    }
}
