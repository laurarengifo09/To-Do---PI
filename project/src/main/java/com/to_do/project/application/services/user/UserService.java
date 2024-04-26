package com.to_do.project.application.services.user;

import com.to_do.project.domain.entities.auth.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
}
