package com.recipes.app.repository;

import com.recipes.app.entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserTable, String> {

    UserTable findByUsername(String username);
}
