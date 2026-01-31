package com.example.Library.Management.System.service.Impl;

import com.example.Library.Management.System.entity.User;
import com.example.Library.Management.System.repository.UserRepository;
import com.example.Library.Management.System.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        //hahpassword
        String hashedPassowrd = passwordEncoder.encode(user.getPassword());
        //set password
        user.setPassword(hashedPassowrd);
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
