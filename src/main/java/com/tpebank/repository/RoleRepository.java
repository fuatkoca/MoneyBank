package com.tpebank.repository;

import com.tpebank.domain.Role;
import com.tpebank.domain.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByName(RoleType roleType);

}
