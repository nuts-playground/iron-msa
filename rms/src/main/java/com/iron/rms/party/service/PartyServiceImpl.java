package com.iron.rms.party.service;

import com.iron.rms.party.repository.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartyServiceImpl {

    private final PartyRepository partyRepository;


}
