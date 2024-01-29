package lk.ijse.poswebbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lk.ijse.poswebbackend.dto.UserPwdDto;
import lk.ijse.poswebbackend.entity.User;
import lk.ijse.poswebbackend.repository.UserRepository;
import lk.ijse.poswebbackend.security.jwt.JwtUtils;
import lk.ijse.poswebbackend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User changePassword(UserPwdDto userPwdDto) {
        User user = userRepository.findById(userPwdDto.getId()).orElse(null);

        if (passwordEncoder.matches(userPwdDto.getOldPassword(), user.getPassword())) {

            String password = passwordEncoder.encode(userPwdDto.getNewPassword());
            user.setPassword(password);
            return userRepository.save(user);
            
        }

        return null;
        
    }

    @Override
    public User findUserByJwt(String jwt) {

        if (StringUtils.hasText(jwt) && jwt.startsWith("Bearer ")) {
            String username = jwtUtils.getUserNameFromJwtToken(jwt.substring(7));
            User user = userRepository.findByUsername(username).orElse(null);
            return user;
        } else {
            return null;
        }

    }

}
