package com.hsleiden.iprwc.api.repo;

import com.hsleiden.iprwc.api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
