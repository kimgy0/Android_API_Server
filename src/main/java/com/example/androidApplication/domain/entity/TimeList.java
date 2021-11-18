package com.example.androidApplication.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "times")
public class TimeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_id")
    private Long id;
    private LocalDate alarmTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public TimeList(LocalDate alarmTime) {
        this.alarmTime = alarmTime;
    }

    public void addTimeList(Group group){
        group.getTimeList().add(this);
        if(this.group == null){
            this.group = group;
        }
    }

    public static void createTimeList(Group group, ArrayList<TimeList> timeLists){
        timeLists.stream().forEach(t -> t.addTimeList(group));
    }
}
