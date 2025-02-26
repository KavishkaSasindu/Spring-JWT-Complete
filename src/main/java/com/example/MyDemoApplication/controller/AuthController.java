package com.example.MyDemoApplication.controller;

import com.example.MyDemoApplication.dto.ResponseData;
import com.example.MyDemoApplication.dto.UserResponseDto;
import com.example.MyDemoApplication.model.UserModel;
import com.example.MyDemoApplication.service.AuthService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Data
@NoArgsConstructor
@RestController
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

//    register controller
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserModel userModel){
       try{
           if(userModel.getUsername().isBlank()  && userModel.getPassword().isBlank()) {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData("Empty username or password"));
           }
           UserModel user = authService.registerUser(userModel);
           if(user == null) {
               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData("User already exists"));
           }

           return ResponseEntity.status(HttpStatus.OK).body(user);

       }catch(Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
       }
    }

//    login user
    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody UserModel userModel){
        try{
            if(userModel.getUsername().isBlank()  && userModel.getPassword().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData("Empty username or password"));
            }

            String token = authService.LogInUser(userModel);
            if(token == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData("Invalid username or password"));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new UserResponseDto(
                    userModel.getUsername(),
                    token
            ));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseData(e.getMessage()));
        }
    }
}
