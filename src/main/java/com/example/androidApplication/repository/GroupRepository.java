package com.example.androidApplication.repository;

import com.example.androidApplication.domain.dto.jpqldto.MyGroupListQueryDto;
import com.example.androidApplication.domain.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByInviteKey(String key);
    @Query("select new com.example.androidApplication.domain.dto.jpqldto.MyGroupListQueryDto(g.groupName,g.inviteKey,p.absent,p.tardy,p.Master)" +
            " from Participate p" +
            " join p.member m" +
            " join p.group g" +
            " on m.id = :id" )
//    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    List<MyGroupListQueryDto> findMyAllGroup(@Param("id") Long id);


}
