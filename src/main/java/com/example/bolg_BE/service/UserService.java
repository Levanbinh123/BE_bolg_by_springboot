package com.example.bolg_BE.service;

import com.example.bolg_BE.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findByUserName(String userName);
}
