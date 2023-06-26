package com.hugohirling.jetpms.services;

import com.hugohirling.jetpms.entities.Equipment;
import com.hugohirling.jetpms.entities.Participant;
import com.hugohirling.jetpms.exceptions.WarningException;
import com.hugohirling.jetpms.repositories.EquipmentRepository;
import com.hugohirling.jetpms.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantEquipmentService {

    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;

    public void assignParticipantToEquipment(final Participant participant, final Equipment equipment) throws WarningException {
        if(equipment.getAssignedTo().isPresent()) {
            throw new WarningException("Equipment is already assigned to a participant.");
        }

        equipment.setAssignedTo(participant);
        participant.addEquipment(equipment);
        participantRepository.save(participant);
        equipmentRepository.save(equipment);
    }

    public void dismissParticipantFromEquipment(final Participant participant, final Equipment equipment) throws WarningException {
        if(equipment.getAssignedTo().isEmpty()) {
            throw new WarningException("Equipment isn't assigned to a participant.");
        }
        if(equipment.getAssignedTo().get() != participant) {
            throw new WarningException("Equipment isn't assigned to this participant.");
        }

        equipment.removeAssignedTo();
        participant.removeEquipment(equipment);
        participantRepository.save(participant);
        equipmentRepository.save(equipment);
    }
}
