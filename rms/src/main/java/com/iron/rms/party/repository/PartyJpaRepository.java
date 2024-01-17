package com.iron.rms.party.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class PartyJpaRepository {
	@PersistenceContext
	private EntityManager em;
}
