package com.iron.rms.channel.domain;

import com.iron.rms.activity.domain.Activity;
import com.iron.rms.constant.ApprovalState;
import com.iron.rms.party.domain.Party;
import com.iron.rms.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_CHANNEL")
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Channel {

    @Id
    @GeneratedValue
    @Column(name = "channel_id")
    private Long channelId;

    @Column(name = "channel_name")
    private String channelName;

    @Column(name = "contents")
    private String contents;

    @OneToMany(mappedBy = "channel")
    List<Party> parties = new ArrayList<>();

    @OneToMany(mappedBy = "channel")
    List<Activity> activities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @Enumerated
    @Column(name = "approval_state")
    private ApprovalState approvalState;    // WAITING, APPROVED, DECLIEND, HOLD

    @Column(name = "created_time")
    private LocalDateTime createdTime;
}
