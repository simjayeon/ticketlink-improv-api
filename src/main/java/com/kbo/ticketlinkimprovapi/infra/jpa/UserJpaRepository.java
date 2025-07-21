package com.kbo.ticketlinkimprovapi.infra.jpa;

import com.kbo.ticketlinkimprovapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {
    boolean existsByName(String name);
}
