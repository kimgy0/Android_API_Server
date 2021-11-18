package com.example.androidApplication.domain.entity;

import antlr.debug.ParserTokenAdapter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "studygroup")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;
    private String groupName;
    private Long certifyDayNumber;
    private String comment;

    public void CreateGroup(String groupName, Long certifyDayNumber, String comment) {
        this.groupName = groupName;
        this.certifyDayNumber = certifyDayNumber;
        this.comment = comment;
    }

    public static Group createGroup(String groupName, Long certifyDayNumber, String comment){
        Group group = new Group();
        group.CreateGroup(groupName,certifyDayNumber,comment);
        return group;
    }

    @OneToMany(mappedBy = "group",fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Participate> participates = new ArrayList<>();

    @OneToMany(mappedBy = "group",fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeList> timeList = new ArrayList<>();
}
