package com.hsleiden.iprwc.api.service;

import com.hsleiden.iprwc.api.exception.PasswordIncorrectException;
import com.hsleiden.iprwc.api.exception.PasswordNotNewException;
import com.hsleiden.iprwc.api.model.Role;
import com.hsleiden.iprwc.api.model.User;
import com.hsleiden.iprwc.api.model.dto.RegisterDto;
import com.hsleiden.iprwc.api.model.dto.UserPutPasswordDto;

import java.util.List;

public interface UserService {
    User saveUser(RegisterDto user);
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    void deleteUser(String username);
    User changePassword(UserPutPasswordDto userPutPasswordDto) throws PasswordIncorrectException, PasswordNotNewException;
}
