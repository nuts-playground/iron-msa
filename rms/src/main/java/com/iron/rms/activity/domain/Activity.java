package com.iron.rms.activity.domain;

import com.iron.rms.channel.domain.Channel;
import com.iron.rms.slot.domain.Slot;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "TB_ACTIVITY")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Activity {

    @Id
    @GeneratedValue
    @Column(name = "activity_id")
    private Long activityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "contents")
    private String contents;

    @Column(name = "activity_min")
    private int activityMin;

    @OneToMany(mappedBy = "activity")
    List<Slot> slots = new ArrayList<>();

    @Column(name = "created_time")
    private LocalDateTime createdTime;
}
