package com.example.StoreServer.services;

import com.example.StoreServer.dto.JwtTokenResponse;
import com.example.StoreServer.dto.UserLoginDto;
import com.example.StoreServer.entities.Cart;
import com.example.StoreServer.entities.Product;
import com.example.StoreServer.entities.User;
import com.example.StoreServer.repositories.CartRepository;
import com.example.StoreServer.repositories.UserRepository;
import com.example.StoreServer.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public JwtTokenResponse saveUserAndReturnJwtResponse(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Cart cart = new Cart(user, new ArrayList<>());
        cartRepository.save(cart);

        return jwtUtil.generateJWTResponse(user);
    }

    public JwtTokenResponse login(UserLoginDto loginDto) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
        authenticationManager.authenticate(auth);
        return jwtUtil.generateJWTResponse(userRepository.findByEmail(loginDto.email()).orElseThrow());
    }
}
