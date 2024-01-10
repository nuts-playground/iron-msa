package com.iron.rms.reservation.service;

import com.iron.rms.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartyServiceImpl {

    private final ReservationRepository reservationRepository;


}
