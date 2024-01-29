package lk.ijse.poswebbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.ijse.poswebbackend.dto.UserPwdDto;
import lk.ijse.poswebbackend.entity.User;

@Service
public interface UserService {

    User createUser(User user);
    List<User> getAllUsers();
    User getUser(Long id);
    User changePassword(UserPwdDto userPwdDto);
    User findUserByJwt(String jwt);
}
