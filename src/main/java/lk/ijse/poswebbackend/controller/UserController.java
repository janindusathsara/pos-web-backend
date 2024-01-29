package lk.ijse.poswebbackend.controller;

import org.springframework.web.bind.annotation.RestController;

import lk.ijse.poswebbackend.dto.UserPwdDto;
import lk.ijse.poswebbackend.entity.User;
import lk.ijse.poswebbackend.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/users/change-password")
    public ResponseEntity<User> changePassword(@RequestBody UserPwdDto userPwdDto) {
        return ResponseEntity.ok().body(userService.changePassword(userPwdDto));
    }
    
    @GetMapping("/getuser")
    public ResponseEntity<User> getUserFromToken(@RequestHeader("Authorization") String jwt) {
        return ResponseEntity.ok().body(userService.findUserByJwt(jwt));
    }
    
}
