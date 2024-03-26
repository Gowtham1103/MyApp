package com.redweber.backendservice.security;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,Long> {
    public boolean existsByUsername(String username);
    public User findByUsername(String username);
}
