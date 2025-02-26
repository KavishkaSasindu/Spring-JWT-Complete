package com.example.MyDemoApplication.controller;

import com.example.MyDemoApplication.dto.ResponseData;
import com.example.MyDemoApplication.model.UserModel;
import com.example.MyDemoApplication.service.UserService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Data
@NoArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    getAll users
    @GetMapping("/allUsers")
    public ResponseEntity<?> getAll() {
        try{
            List<UserModel> users = userService.getAllUsers();
            if(users != null) {
                return ResponseEntity.status(HttpStatus.OK).body(users);
            }

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseData("No users found"));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseData(e.getMessage()));
        }
    }
}
