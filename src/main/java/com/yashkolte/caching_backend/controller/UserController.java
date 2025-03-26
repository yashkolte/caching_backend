package com.yashkolte.caching_backend.controller;
import com.yashkolte.caching_backend.entity.User;
import com.yashkolte.caching_backend.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 🟢 GET USER (Uses cache)
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // 🟡 GET ALL USERS (No caching)
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 🔵 ADD USER (Clears cache)
    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // 🔴 DELETE USER (Removes from cache)
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted and cache cleared!";
    }
}