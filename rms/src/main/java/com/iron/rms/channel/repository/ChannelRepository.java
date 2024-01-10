package com.iron.rms.channel.repository;

import com.iron.rms.activity.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Activity, Long> {
}
