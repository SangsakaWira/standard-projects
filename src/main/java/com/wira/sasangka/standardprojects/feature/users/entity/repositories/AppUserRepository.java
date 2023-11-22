package com.wira.sasangka.standardprojects.feature.users.entity.repositories;

import com.wira.sasangka.standardprojects.feature.users.entity.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    Optional<AppUser> findUserByUsername(String username);
}