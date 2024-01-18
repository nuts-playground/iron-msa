package com.iron.rms.slot.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class SlotJpaRepository {

	@PersistenceContext
	EntityManager em;


}
