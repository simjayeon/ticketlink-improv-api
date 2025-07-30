package com.kbo.ticketlinkimprovapi.domain.repository;

import com.kbo.ticketlinkimprovapi.domain.entity.User;

public interface IUserRepository {
    boolean existsByName(String name);

    void save(User user);

    User findByEmail(String email);

    User findById(Integer userId);
}
