package com.anshuman.graphqldemo.model.repository;


import com.anshuman.graphqldemo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserNameIgnoreCase(@NonNull String userName);

    Optional<User> findByEmail(String email);
}
