package com.example.androidApplication.domain.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Setter
@Table(name = "participates")
public class Participate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participate_id")
    private Long id;

    private boolean Master;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public static Participate CreateParticipate(boolean master, Member member, Group group) {
        Participate participate = new Participate();
        participate.participateMember(member,group);
        participate.setMaster(master);
        return participate;
    }

    protected void participateMember(Member member, Group group){
        this.member = member;
        if(member.getParticipate().contains(this) == false){
            member.getParticipate().add(this);
        }
        this.group = group;
        if(!group.getParticipates().contains(this) == false){
            group.getParticipates().add(this);
        }
    }
}
