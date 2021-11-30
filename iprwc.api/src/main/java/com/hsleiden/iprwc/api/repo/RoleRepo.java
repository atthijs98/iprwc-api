package com.hsleiden.iprwc.api.repo;

import com.hsleiden.iprwc.api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
