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

    @Override
    public User findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public User findById(Integer userId) {
        return userJpaRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }
}
