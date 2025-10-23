package com.example.Library.Management.System.service;

import com.example.Library.Management.System.entity.User;

public interface UserService {
    User saveUser(User user);

    User getUserByEmail(String email);

    User getUserById(Long id);
}
