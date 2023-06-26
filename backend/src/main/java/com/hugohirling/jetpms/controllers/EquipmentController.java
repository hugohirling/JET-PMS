package com.hugohirling.jetpms.controllers;

import com.hugohirling.jetpms.entities.Equipment;
import com.hugohirling.jetpms.exceptions.RecordNotFoundException;
import com.hugohirling.jetpms.responsemodels.FullEquipmentResponse;
import com.hugohirling.jetpms.services.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/equip")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    private final Logger logger = LoggerFactory.getLogger(EquipmentController.class);

    @GetMapping()
    public List<FullEquipmentResponse> getEquipments() {
        return equipmentService.getAll().stream().map(FullEquipmentResponse::getFromEquipment).toList();
    }

    @GetMapping("/{equipmentid}")
    public FullEquipmentResponse getEquiptmentById(@PathVariable("equipmentid") Integer equipmentId) {
        Optional<Equipment> optional = equipmentService.get(equipmentId);
        if(optional.isEmpty()) {
            this.logger.warn(String.format("Get-Request: Get equipment with id: equipment with id-%s doesn't exist", equipmentId));
            throw new RecordNotFoundException();
        }
        return FullEquipmentResponse.getFromEquipment(optional.get());
    }

    @PostMapping(path="/add")
    public Map<String, String> addNewEquipment(@RequestBody final Equipment equipment) {
        Map<String, String> returnMap = new HashMap<>();

        this.logger.info("Added new equipment: " + equipment.getEquipmentType().name() + " size: " + equipment.getSize());

        returnMap.put("allowed", "true");
        returnMap.put("message", "Successfully");
        equipmentService.addNew(equipment);

        return returnMap;
    }
}
