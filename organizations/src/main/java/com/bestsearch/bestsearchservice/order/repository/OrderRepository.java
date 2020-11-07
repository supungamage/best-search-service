package com.bestsearch.bestsearchservice.order.repository;

import com.bestsearch.bestsearchservice.order.model.Order;
import com.bestsearch.bestsearchservice.order.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderRefAndOrganizationTypeId(String orderRef, long organizationTypeId);

    @Modifying
    @Query(value = "UPDATE Order o SET o.status = ?2 WHERE o.id = ?1")
    void updateOrderStatus(long id, Status toStatus);
}
