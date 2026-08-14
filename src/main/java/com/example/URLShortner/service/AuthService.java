package com.example.URLShortner.service;

import com.example.URLShortner.dto.LoginRequest;
import com.example.URLShortner.dto.LoginResponse;
import com.example.URLShortner.dto.SignupRequest;
import com.example.URLShortner.dto.SignupResponse;
import com.example.URLShortner.entity.Role;
import com.example.URLShortner.entity.User;
import com.example.URLShortner.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtService jwtService){
   this.userRepository=userRepository;
   this.passwordEncoder=passwordEncoder;
   this.jwtService=jwtService;
    }
   public LoginResponse login(LoginRequest request){
        User  user=userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new RuntimeException("invalid email"));

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("invalid email or password");
        }
       String token = jwtService.generateToken(user.getEmail());
        return new LoginResponse(user.getId(),user.getName(),user.getEmail(),user.getRole(),token);
   }

    public SignupResponse signup(SignupRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("user Already existed");
        }
        User user=new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_USER);

        User saveduser=userRepository.save(user);
        return new SignupResponse(saveduser.getId(),saveduser.getName(),saveduser.getEmail(),saveduser.getRole());
    }
}
