package com.hugohirling.jetpms.services;

import com.hugohirling.jetpms.entities.Participant;
import com.hugohirling.jetpms.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public List<Participant> getAll() {
        List<Participant> list = new ArrayList<>();
        participantRepository.findAll().forEach(list::add);
        return list;
    }

    public Optional<Participant> get(final int id) {
        return participantRepository.findById(id);
    }

    public void addNew(final Participant participant) {
        participantRepository.save(participant);
    }
}
