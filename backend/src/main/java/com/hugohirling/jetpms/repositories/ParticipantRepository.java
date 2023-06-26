package com.hugohirling.jetpms.repositories;

import com.hugohirling.jetpms.entities.Participant;
import org.springframework.data.repository.CrudRepository;

public interface ParticipantRepository extends CrudRepository<Participant, Integer> {
}
