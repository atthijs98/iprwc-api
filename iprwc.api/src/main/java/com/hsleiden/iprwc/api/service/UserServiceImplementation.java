package com.hsleiden.iprwc.api.service;

import com.hsleiden.iprwc.api.exception.PasswordIncorrectException;
import com.hsleiden.iprwc.api.exception.PasswordNotNewException;
import com.hsleiden.iprwc.api.model.MyUserPrinciple;
import com.hsleiden.iprwc.api.model.Role;
import com.hsleiden.iprwc.api.model.User;
import com.hsleiden.iprwc.api.model.dto.RegisterDto;
import com.hsleiden.iprwc.api.model.dto.UserPutPasswordDto;
import com.hsleiden.iprwc.api.repo.RoleRepo;
import com.hsleiden.iprwc.api.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(RegisterDto registerDto) {

        User user = new User();

        user.setUsername(registerDto.getUsername());
        user.setName(registerDto.getName());
        log.info("Saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(Collections.singletonList(roleRepo.findByName("ROLE_USER")));

        return userRepo.save(user);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User: %s, not found", username)
                        )
                );
        Role role = roleRepo.findByName(roleName);
        log.info("Adding role {} to user {}", role.getName(), user.getName());
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Getting user from database.");
        return userRepo.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User: %s, not found", username)
                        )
                );
    }

    @Override
    public List<User> getUsers() {
        log.info("Getting all users from database.");
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User: %s, not found", username)
                        )
                );
        log.info("Deleting user {} from database.", user.getName());
        userRepo.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User: %s, not found", username)
                        )
                );
    }

    @Override
    public User changePassword(UserPutPasswordDto userPutPasswordDto) throws PasswordIncorrectException, PasswordNotNewException {
        User user = findLoggedInUser();
        if (checkIfOldPasswordIsCorrect(userPutPasswordDto.getOldPassword(), user)) {
            if (!checkIfPasswordIsNew(userPutPasswordDto.getNewPassword(), user)) {
                user.setPassword(
                        passwordEncoder.encode(userPutPasswordDto.getNewPassword())
                );
                return userRepo.save(user);
            }
            throw new PasswordNotNewException("New password can't be the same as old password.");
        }
        throw new PasswordIncorrectException("Old password is incorrect.");
    }

    private boolean checkIfOldPasswordIsCorrect(String oldPassword, User user) {
        return passwordEncoder.encode(oldPassword).equals(user.getPassword());
    }

    private boolean checkIfPasswordIsNew(String newPassword, User user) {
        return passwordEncoder.encode(newPassword).equals(user.getPassword());
    }

    public User findLoggedInUser() throws UsernameNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        return find(currentPrincipalName);
    }

    public User find(String username) throws UsernameNotFoundException {
        return userRepo
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User: %s, not found", username)
                        )
                );
    }
}
