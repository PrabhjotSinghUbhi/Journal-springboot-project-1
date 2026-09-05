package me.prabh.journal.repository;

import jakarta.validation.constraints.NotNull;
import me.prabh.journal.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(@NotNull String username);
}
