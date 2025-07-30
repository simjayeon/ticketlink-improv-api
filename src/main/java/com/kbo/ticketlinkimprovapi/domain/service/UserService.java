package com.kbo.ticketlinkimprovapi.domain.service;

import com.kbo.ticketlinkimprovapi.domain.entity.User;
import com.kbo.ticketlinkimprovapi.domain.repository.IUserRepository;
import com.kbo.ticketlinkimprovapi.interfaces.dto.ReqLogin;
import com.kbo.ticketlinkimprovapi.interfaces.dto.ReqSignup;
import com.kbo.ticketlinkimprovapi.interfaces.dto.ResLogin;
import com.kbo.ticketlinkimprovapi.interfaces.dto.ResUserInfo;
import com.kbo.ticketlinkimprovapi.support.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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
        User user = userRepository.findByEmail(req.getEmail());

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        // (2) JWT 발급
        String token = JwtUtil.generateToken(req.getEmail());

        // (3) 응답 반환
        return ResponseEntity.ok(new ResLogin(token));
    }

    public ResponseEntity<?> getUserInfo(Integer userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        ResUserInfo userInfo = ResUserInfo.of(user);
        return ResponseEntity.ok(userInfo);
    }
}
