package com.hsleiden.iprwc.api.repo;

import com.hsleiden.iprwc.api.model.Order;
import com.hsleiden.iprwc.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long> {
    Optional<Iterable<Order>> findByUserId(Long userId);
}
