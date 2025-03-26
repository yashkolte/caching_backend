package com.yashkolte.caching_backend.repo;
import com.yashkolte.caching_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
