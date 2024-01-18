package com.iron.rms.activity.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityJpaRepository {

	@PersistenceContext
	EntityManager em;


}
