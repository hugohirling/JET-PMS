package com.hugohirling.jetpms.services;

import com.hugohirling.jetpms.entities.Equipment;
import com.hugohirling.jetpms.repositories.EquipmentRepository;
import com.hugohirling.jetpms.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Equipment> getAll() {
        List<Equipment> list = new ArrayList<>();
        equipmentRepository.findAll().forEach(list::add);
        return list;
    }

    public Optional<Equipment> get(final int id) {
        return equipmentRepository.findById(id);
    }

    public void save(final Equipment equipment) {
        equipmentRepository.save(equipment);
    }
}
