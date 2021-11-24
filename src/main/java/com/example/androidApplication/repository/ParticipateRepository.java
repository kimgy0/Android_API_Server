package com.example.androidApplication.repository;

import com.example.androidApplication.domain.entity.Member;
import com.example.androidApplication.domain.entity.Participate;
import com.example.androidApplication.repository.custom.ParticipateRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipateRepository extends JpaRepository<Participate, Long>, ParticipateRepositoryCustom {
    @Query("select p from Participate p " +
            "join fetch p.group g " +
            "join fetch p.member m " +
            "where g.inviteKey=:inviteKey and m.id =:memberId")
    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    public Optional<Participate> findParticipateInfo(@Param("inviteKey") String inviteKey,
                                                     @Param("memberId") Long memberId);



}
