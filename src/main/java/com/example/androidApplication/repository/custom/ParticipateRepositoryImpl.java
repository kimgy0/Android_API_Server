package com.example.androidApplication.repository.custom;

import com.example.androidApplication.domain.dto.jpqldto.MyGroupInfoQueryDto;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.QueryHint;
import java.util.List;

public class ParticipateRepositoryImpl implements ParticipateRepositoryCustom{

    @PersistenceContext
    private EntityManager em;

    @Override
//    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    public List<MyGroupInfoQueryDto> findMyGroup(String key) {
        return em.createQuery("select new com.example.androidApplication.domain.dto.jpqldto.MyGroupInfoQueryDto(g.comment,g.groupName,p.absent,p.tardy,p.Master,m.id,m.username)" +
                " from Participate p" +
                " join p.member m" +
                " join p.group g" +
                " on g.inviteKey =:key" +
                        " and m.id = p.member.id", MyGroupInfoQueryDto.class)
                .setParameter("key",key)
                .getResultList();
    }
}
