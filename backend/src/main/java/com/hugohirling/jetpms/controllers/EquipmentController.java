package com.hugohirling.jetpms.controllers;

import com.hugohirling.jetpms.entities.Equipment;
import com.hugohirling.jetpms.entities.Participant;
import com.hugohirling.jetpms.exceptions.RecordNotFoundException;
import com.hugohirling.jetpms.exceptions.WarningException;
import com.hugohirling.jetpms.models.response.FullEquipmentResponse;
import com.hugohirling.jetpms.services.EquipmentService;
import com.hugohirling.jetpms.services.ParticipantEquipmentService;
import com.hugohirling.jetpms.services.ParticipantService;
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
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private ParticipantEquipmentService participantEquipmentService;

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

    @PostMapping(path="addEquip/{participantid}/{equipmentid}")
    public Map<String, String> addEquipmentToParticipant(@PathVariable("participantid") Integer participantId, @PathVariable("equipmentid") Integer equipmentId) {
        Map<String, String> returnMap = new HashMap<>();

        Optional<Participant> participantOptional = participantService.get(participantId);
        Optional<Equipment> equipmentOptional = equipmentService.get(equipmentId);
        if(participantOptional.isEmpty()) {
            this.logger.warn(String.format("Get-Request: Get participant with id: participant with id-%s doesn't exist", participantId));
            throw new RecordNotFoundException();
        }
        if(equipmentOptional.isEmpty()) {
            this.logger.warn(String.format("Get-Request: Get equipment with id: equipment with id-%s doesn't exist", equipmentId));
            throw new RecordNotFoundException();
        }

        try {
            participantEquipmentService.assignParticipantToEquipment(participantOptional.get(), equipmentOptional.get());

            this.logger.info("Added equipment to participant");

            returnMap.put("allowed", "true");
            returnMap.put("message", "Successfully");
        }catch(WarningException ex) {
            this.logger.info(ex.getMessage());

            returnMap.put("allowed", "false");
            returnMap.put("message", ex.getMessage());
        }

        return returnMap;
    }

    @PostMapping(path="removeEquip/{participantid}/{equipmentid}")
    public Map<String, String> removeEquipmentFromParticipant(@PathVariable("participantid") Integer participantId, @PathVariable("equipmentid") Integer equipmentId) {
        Map<String, String> returnMap = new HashMap<>();

        Optional<Participant> participantOptional = participantService.get(participantId);
        Optional<Equipment> equipmentOptional = equipmentService.get(equipmentId);
        if(participantOptional.isEmpty()) {
            this.logger.warn(String.format("Get-Request: Get participant with id: participant with id-%s doesn't exist", participantId));
            throw new RecordNotFoundException();
        }
        if(equipmentOptional.isEmpty()) {
            this.logger.warn(String.format("Get-Request: Get equipment with id: equipment with id-%s doesn't exist", equipmentId));
            throw new RecordNotFoundException();
        }

        try {
            participantEquipmentService.dismissParticipantFromEquipment(participantOptional.get(), equipmentOptional.get());

            this.logger.info("Removed equipment from participant");

            returnMap.put("allowed", "true");
            returnMap.put("message", "Successfully");
        }catch(WarningException ex) {
            this.logger.info(ex.getMessage());

            returnMap.put("allowed", "false");
            returnMap.put("message", ex.getMessage());
        }

        return returnMap;
    }
}
