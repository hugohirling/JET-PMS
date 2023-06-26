package com.hugohirling.jetpms.responsemodels;

import com.hugohirling.jetpms.entities.Equipment;
import com.hugohirling.jetpms.entities.EquipmentType;

public class FullEquipmentResponse extends SimpleEquipmentResponse{

    private final SimpleParticipantResponse participant;

    public FullEquipmentResponse(Integer eid, EquipmentType equipmentType, String size, SimpleParticipantResponse participant) {
        super(eid, equipmentType, size);
        this.participant = participant;
    }

    public SimpleParticipantResponse getParticipant() {
        return participant;
    }

    public static FullEquipmentResponse getFromEquipment(final Equipment equipment) {
        return new FullEquipmentResponse(
                equipment.getEid(),
                equipment.getEquipmentType(),
                equipment.getSize(),
                equipment.getAssignedTo().isPresent() ? SimpleParticipantResponse.getFromParticipant(equipment.getAssignedTo().get()) : null
        );
    }
}
