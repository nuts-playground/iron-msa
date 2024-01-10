package com.iron.rms.slot.service;

import com.iron.rms.slot.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl {

    private final SlotRepository slotRepository;


}
