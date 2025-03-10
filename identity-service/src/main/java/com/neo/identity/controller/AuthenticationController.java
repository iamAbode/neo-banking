package com.neo.identity.controller;

import com.neo.common.annotation.WrapResponse;
import com.neo.common.dto.CustomerDTO;
import com.neo.identity.entity.User;
import com.neo.identity.model.AuthRequest;
import com.neo.identity.model.AuthResponse;
import com.neo.identity.util.JwtUtil;
import com.neo.identity.service.UserInfoService;
import com.neo.identity.util.UserDetailUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ABODE
 * @Date 2025/03/09 6:56 PM
 */
@RestController
@Validated
@WrapResponse
@RequestMapping("/api/identity")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserInfoService userInfoService;
    private final JwtUtil jwtUtil;
    private final UserDetailUtil userDetailUtil;

    @GetMapping("/welcome")
    public String home(HttpServletRequest request){
        return "Welcome to Blue harvest page! ";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody User userInfo) {
        return userInfoService.addUser(userInfo);
    }

    @GetMapping("/verify")
    public User getUser(@RequestParam("username") String username){
        return userDetailUtil.getUserDetail(username);
    }

    @PostMapping("/generateToken")
    public AuthResponse authenticateAndGetToken(@Valid @RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            String token = jwtUtil.generateToken(authRequest.getUsername());
            return buildAuthResponse(token);

        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    private AuthResponse buildAuthResponse(String token) {
        return AuthResponse.builder().accessToken(token)
                .tokenType("Bearer").expiresIn(jwtUtil.extractExpiration(token))
                .username(jwtUtil.extractUsername(token))
                .build();
    }

}
