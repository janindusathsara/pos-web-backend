package lk.ijse.poswebbackend.controller;

import org.springframework.web.bind.annotation.RestController;

import lk.ijse.poswebbackend.dto.UserPwdDto;
import lk.ijse.poswebbackend.entity.User;
import lk.ijse.poswebbackend.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/users/{id}/change-password")
    public ResponseEntity<User> changePassword(@PathVariable Long id, @RequestBody UserPwdDto userPwdDto) {
        return ResponseEntity.ok().body(userService.changePassword(id,userPwdDto));
    }
    
}
