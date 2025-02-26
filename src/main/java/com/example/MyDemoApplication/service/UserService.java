package com.example.MyDemoApplication.service;

import com.example.MyDemoApplication.model.UserModel;
import com.example.MyDemoApplication.repo.UserRepo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@NoArgsConstructor
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

//    get all users
    public List<UserModel> getAllUsers() {
        return userRepo.findAll();
    }
}
