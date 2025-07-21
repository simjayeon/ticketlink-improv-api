package com.kbo.ticketlinkimprovapi.domain.service;

import com.kbo.ticketlinkimprovapi.domain.entity.User;
import com.kbo.ticketlinkimprovapi.domain.repository.IUserRepository;
import com.kbo.ticketlinkimprovapi.interfaces.dto.ReqSignup;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;

    public void signUp(ReqSignup req) {
        if (userRepository.existsByName(req.getName())) {
            throw new RuntimeException("이미 존재하는 사용자입니다.");
        }

        String encodedPassword = passwordEncoder.encode(req.getPassword());

        User user = new User();
        user.setName(req.getName());
        user.setPassword(encodedPassword);
        user.setEmail(req.getEmail());
        user.setBirthday(req.getBirthDay());
        user.setPhoneNumber(req.getPhoneNumber());
        userRepository.save(user);
    }
}
