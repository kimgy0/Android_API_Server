package com.example.androidApplication.repository;

import com.example.androidApplication.domain.entity.Group;
import com.example.androidApplication.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByInviteKey(String key);
}
