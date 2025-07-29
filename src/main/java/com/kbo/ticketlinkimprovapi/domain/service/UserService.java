package com.kbo.ticketlinkimprovapi.domain.service;

import com.kbo.ticketlinkimprovapi.domain.entity.User;
import com.kbo.ticketlinkimprovapi.domain.repository.IUserRepository;
import com.kbo.ticketlinkimprovapi.interfaces.dto.ReqLogin;
import com.kbo.ticketlinkimprovapi.interfaces.dto.ReqSignup;
import com.kbo.ticketlinkimprovapi.interfaces.dto.ResLogin;
import com.kbo.ticketlinkimprovapi.support.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<?> login(ReqLogin req) {
        if (!req.getEmail().equals("test@test.com") ||
                !req.getPassword().equals("1234")) {
            return ResponseEntity.status(401).body("이메일 또는 비밀번호 불일치");
        }

        // (2) JWT 발급
        String token = JwtUtil.generateToken(req.getEmail());

        // (3) 응답 반환
        return ResponseEntity.ok(new ResLogin(token));
    }
}
