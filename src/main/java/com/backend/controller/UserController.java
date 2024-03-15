package com.backend.controller;

import com.backend.entities.Demande;
import com.backend.entities.User;
import com.backend.service.DemandeService;
import com.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @PreAuthorize("hasAuthority('SCOPE_USER')")
    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.userService.getOneUser(id));
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    ResponseEntity<Long> saveUser(@RequestBody @Validated User user) {
        return ResponseEntity.ok(this.userService.createUser(user));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    ResponseEntity<Long> updateUser(@PathVariable("id") long id, @RequestBody @Validated User user) {
        return ResponseEntity.ok(userService.editUser(user, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.accepted().build();
    }

}
