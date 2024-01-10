package com.iron.rms.channel.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ChannelJpaRepository {

    @PersistenceContext
    EntityManager em;


}
