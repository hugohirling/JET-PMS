package com.hugohirling.jetpms.responsemodels;

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
}
