package com.example.androidApplication.repository;

import com.example.androidApplication.domain.entity.Member;
import com.example.androidApplication.domain.entity.Participate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipateRepository extends JpaRepository<Participate, Long> {
}
