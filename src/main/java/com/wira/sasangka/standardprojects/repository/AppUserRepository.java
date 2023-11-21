package com.wira.sasangka.standardprojects.repository;

import com.wira.sasangka.standardprojects.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    Optional<AppUser> findUserByUsername(String username);
}