package com.hugohirling.jetpms.responsemodels;

import com.hugohirling.jetpms.entities.Equipment;
import com.hugohirling.jetpms.entities.EquipmentType;

public class SimpleEquipmentResponse {

    private final Integer eid;
    private final EquipmentType equipmentType;
    private final String size;

    public SimpleEquipmentResponse(Integer eid, EquipmentType equipmentType, String size) {
        this.eid = eid;
        this.equipmentType = equipmentType;
        this.size = size;
    }

    public Integer getEid() {
        return eid;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public String getSize() {
        return size;
    }

    public static SimpleEquipmentResponse getFromEquipment(final Equipment equipment) {
        return new SimpleEquipmentResponse(
                equipment.getEid(),
                equipment.getEquipmentType(),
                equipment.getSize()
        );
    }
}
