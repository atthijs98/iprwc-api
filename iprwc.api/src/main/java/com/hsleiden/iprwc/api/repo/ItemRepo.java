package com.hsleiden.iprwc.api.repo;

import com.hsleiden.iprwc.api.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {
}
