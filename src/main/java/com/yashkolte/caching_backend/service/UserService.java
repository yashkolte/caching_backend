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

    // 🟢 GET USER (Fetch from cache if available, else query DB and store in cache)
    @Cacheable(value = "users", key = "#id")
    public Optional<User> getUserById(Long id) {
        System.out.println("Fetching from Database...");
        return userRepository.findById(id);
    }

    // 🟡 GET ALL USERS (No caching because list might change frequently)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 🔵 ADD USER (Evicts cache so that new user list is fetched from DB)
    @CacheEvict(value = "users", allEntries = true)
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // 🔴 DELETE USER (Remove the specific user from cache when deleted)
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}