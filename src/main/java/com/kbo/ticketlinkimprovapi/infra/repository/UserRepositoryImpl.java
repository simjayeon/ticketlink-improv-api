package com.kbo.ticketlinkimprovapi.infra.repository;

import com.kbo.ticketlinkimprovapi.domain.entity.User;
import com.kbo.ticketlinkimprovapi.domain.repository.IUserRepository;
import com.kbo.ticketlinkimprovapi.infra.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements IUserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean existsByName(String name) {
        return userJpaRepository.existsByName(name);
    }

    @Override
    public void save(User user) {
        userJpaRepository.save(user);
    }
}
