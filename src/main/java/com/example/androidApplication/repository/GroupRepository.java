package com.example.androidApplication.repository;

import com.example.androidApplication.domain.dto.GroupManageDto;
import com.example.androidApplication.domain.dto.jpqldto.MyGroupList;
import com.example.androidApplication.domain.entity.Group;
import com.example.androidApplication.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByInviteKey(String key);
    @Query("select new com.example.androidApplication.domain.dto.jpqldto.MyGroupList(g.groupName,g.inviteKey,p.absent,p.tardy,p.Master)" +
            " from Participate p" +
            " join p.member m" +
            " join p.group g" +
            " on m.id = :id" )
    List<MyGroupList> findMyAllGroup(@Param("id") Long id);

}
