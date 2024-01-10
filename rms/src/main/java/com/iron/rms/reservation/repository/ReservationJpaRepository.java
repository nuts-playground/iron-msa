package com.iron.rms.reservation.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationJpaRepository {

    @PersistenceContext
    EntityManager em;


}
