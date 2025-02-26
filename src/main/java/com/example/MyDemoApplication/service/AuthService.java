package com.example.MyDemoApplication.service;

import com.example.MyDemoApplication.Jwt.JwtService;
import com.example.MyDemoApplication.dto.ResponseData;
import com.example.MyDemoApplication.model.UserModel;
import com.example.MyDemoApplication.repo.UserRepo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Data
@Service
public class AuthService {

    private UserRepo userRepo;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @Autowired
    public AuthService(UserRepo userRepo,AuthenticationManager authenticationManager,JwtService jwtService) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

//    register user
    public UserModel registerUser(UserModel user) {
       UserModel userModel = userRepo.findByUsername(user.getUsername());
       if(userModel != null) {
           return null;
       }

       user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       return userRepo.save(user);
    }

//    login user
    public String LogInUser(UserModel user){
        UserModel userModel = userRepo.findByUsername(user.getUsername());
        if(userModel != null) {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            if(authentication.isAuthenticated()) {
                return jwtService.generateToken(user.getUsername());
            }
        }

        return null;
    }
}
