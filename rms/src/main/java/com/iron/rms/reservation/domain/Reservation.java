package com.iron.rms.reservation.domain;

import com.iron.rms.member.domain.Member;
import com.iron.rms.slot.domain.Slot;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_RESERVATION")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    private Long reservationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot")
    private Slot slot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "created_time")
    private LocalDateTime createdTime;
}
