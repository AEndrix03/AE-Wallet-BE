package com.aendrix.aewallet.services.security;

import com.aendrix.aewallet.entity.WltUser;
import com.aendrix.aewallet.repositories.auth.UserRepository;
import com.aendrix.aewallet.services.UserProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtRequestInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProvider userProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = extractTokenFromRequest(request);
        if (token != null && !this.jwtService.isTokenExpired(token)) {
            String username = this.jwtService.extractUsername(token);
            WltUser userDetails = this.userRepository.getUserByMail(username);

            if (jwtService.isTokenValid(token, userDetails)) {
                this.userProvider.setUserDto(this.jwtService.extractUserObj(token));
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        this.userProvider.clear();
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
