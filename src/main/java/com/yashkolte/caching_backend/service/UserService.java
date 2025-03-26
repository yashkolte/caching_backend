package com.yashkolte.caching_backend.service;
import com.yashkolte.caching_backend.entity.User;
import com.yashkolte.caching_backend.repo.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Fetch user by ID with caching
    @Cacheable(value = "users", key = "#id")
    public Optional<User> getUserById(Long id) {
        System.out.println("Fetching from Database...");
        return userRepository.findById(id);
    }

    // Get all users (without caching)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Save user and update cache
    @CacheEvict(value = "users", allEntries = true)
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Delete user and clear cache
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
