package com.hugohirling.jetpms.entities;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
public class Equipment {

    @Id
    @GeneratedValue
    private Integer eid;

    @Enumerated(EnumType.STRING)
    private EquipmentType equipmentType;
    private String size;
    @ManyToOne
    @JoinColumn(name = "assigned_to_uid")
    private Participant assignedTo;



    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Optional<Participant> getAssignedTo() {
        return Optional.ofNullable(assignedTo);
    }

    public void setAssignedTo(Participant assignedTo) {
        this.assignedTo = assignedTo;
    }
    public void removeAssignedTo() {
        this.assignedTo = null;
    }
}
