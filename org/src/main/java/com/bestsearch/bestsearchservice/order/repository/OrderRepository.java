package com.bestsearch.bestsearchservice.order.repository;

import com.bestsearch.bestsearchservice.order.model.Order;
import com.bestsearch.bestsearchservice.order.model.enums.OrderStatus;
import com.bestsearch.bestsearchservice.organization.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderRefAndOrganizationTypeId(String orderRef, long organizationTypeId);

    @Modifying
    @Query(value = "UPDATE Order o SET o.orderStatus = ?2 WHERE o.id IN ?1")
    void updateOrderStatus(long id, OrderStatus toStatus);
}
