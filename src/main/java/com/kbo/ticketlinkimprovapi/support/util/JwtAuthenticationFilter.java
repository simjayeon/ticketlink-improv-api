package com.kbo.ticketlinkimprovapi.support.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();

        // 로그인과 회원가입은 JWT 검사 제외
        if (path.startsWith("/api/v1/user/login") || path.startsWith("/api/v1/user/signup")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Authorization 헤더에서 Bearer 토큰을 추출
        String header = request.getHeader("Authorization");

        // 헤더가 null이 아니고 Bearer로 시작하는지 확인
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            // 토큰에서 사용자 이메일을 추출하는 메서드 호출
            String userEmail = JwtUtil.generateToken(token);

            // 사용자 이메일이 null이 아니고 현재 인증 정보가 없다면 UsernamePasswordAuthenticationToken 생성
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userEmail, null, null
                );
                // 인증 세부 정보를 설정
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // SecurityContext에 인증 정보 설정
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }
}