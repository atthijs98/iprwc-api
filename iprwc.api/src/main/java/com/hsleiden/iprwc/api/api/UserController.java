package com.hsleiden.iprwc.api.api;

import com.hsleiden.iprwc.api.exception.PasswordIncorrectException;
import com.hsleiden.iprwc.api.exception.PasswordNotNewException;
import com.hsleiden.iprwc.api.model.Role;
import com.hsleiden.iprwc.api.model.User;
import com.hsleiden.iprwc.api.model.dto.DeleteUserDto;
import com.hsleiden.iprwc.api.model.dto.RoleToUserDto;
import com.hsleiden.iprwc.api.model.dto.UserPutPasswordDto;
import com.hsleiden.iprwc.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addToUser(@RequestBody @Valid RoleToUserDto form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteUserDto form) {
        userService.deleteUser(form.getUsername());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/password")
    public ResponseEntity<User> changePassword(@RequestBody UserPutPasswordDto userPutPasswordDto) throws PasswordNotNewException, PasswordIncorrectException {
        return ResponseEntity.ok()
                .body(userService.changePassword(userPutPasswordDto));

    }

}
