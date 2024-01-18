package com.iron.rms.slot.repository;

import com.iron.rms.slot.domain.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<Slot, Long> {
}
