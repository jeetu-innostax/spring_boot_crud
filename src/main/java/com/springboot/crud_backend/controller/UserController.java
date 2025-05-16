package com.springboot.crud_backend.controller;

import com.springboot.crud_backend.model.User;
import com.springboot.crud_backend.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin("*")
@RestController

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

//    @GetMapping("/user/age/{age}")
//    public List<User> getUserAge(@PathVariable int age) {
//        return userRepository.getUserAge(age); // Calls the repository method
////        return true;
//    }

    @GetMapping("/user/age/{age}")
    public List<User> getUserAge(@PathVariable int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age must be non-negative");
        }
        return userRepository.findUsersByAge(age); // Assumes findByAge exists in repository
    }

    @GetMapping("/user")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    user.setAge(newUser.getAge());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    @DeleteMapping("/user/{id}")
    String delteUser(@PathVariable Long id) {
        if(!userRepository.existsById(id)) {
            return "User Not Found";
        }
        userRepository.deleteById(id);
        return "User Deleted";
    }

}
