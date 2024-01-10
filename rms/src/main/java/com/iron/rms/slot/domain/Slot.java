package com.iron.rms.slot.domain;

import com.iron.rms.activity.domain.Activity;
import com.iron.rms.reservation.domain.Reservation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_SLOT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Slot {

    @Id
    @GeneratedValue
    @Column(name = "slot_id")
    private Long slotId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @Column(name = "date", length = 8, nullable = false)
    private String date;

    @Column(name = "time", length = 4, nullable = false)
    private String time;

    @OneToMany(mappedBy = "slot")
    List<Reservation> reservations = new ArrayList<>();

    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;
}
