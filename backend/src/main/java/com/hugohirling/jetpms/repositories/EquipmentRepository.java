package com.hugohirling.jetpms.repositories;

import com.hugohirling.jetpms.entities.Equipment;
import org.springframework.data.repository.CrudRepository;

public interface EquipmentRepository extends CrudRepository<Equipment, Integer> {
}
