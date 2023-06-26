package com.hugohirling.jetpms.controllers;

import com.hugohirling.jetpms.entities.Equipment;
import com.hugohirling.jetpms.entities.EventDate;
import com.hugohirling.jetpms.entities.Participant;
import com.hugohirling.jetpms.exceptions.RecordNotFoundException;
import com.hugohirling.jetpms.exceptions.WarningException;
import com.hugohirling.jetpms.models.response.FullParticipantResponse;
import com.hugohirling.jetpms.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/parti")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;
    @Autowired
    private ParticipantEquipmentService participantEquipmentService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private EventDateService eventDateService;
    @Autowired
    private ParticipantEventService participantEventService;

    private final Logger logger = LoggerFactory.getLogger(ParticipantController.class);

    @GetMapping()
    public List<FullParticipantResponse> getParticipants() {
        return participantService.getAll().stream().map(FullParticipantResponse::getFromParticipant).toList();
    }

    @GetMapping("/{participantid}")
    public FullParticipantResponse getParticipantById(@PathVariable("participantid") Integer participantId) {
        Optional<Participant> optional = participantService.get(participantId);
        if(optional.isEmpty()) {
            this.logger.warn(String.format("Get-Request: Get participant with id: participant with id-%s doesn't exist", participantId));
            throw new RecordNotFoundException();
        }
        return FullParticipantResponse.getFromParticipant(optional.get());
    }

    @PostMapping(path="/add")
    public Map<String, String> addNewParticipant(@RequestBody final Participant participant) {
        Map<String, String> returnMap = new HashMap<>();

        this.logger.info("Added new participant: " + participant.getFirst() + " " + participant.getLast());

        returnMap.put("allowed", "true");
        returnMap.put("message", "Successfully");
        participantService.addNew(participant);

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

    @PostMapping(path="addDate/{participantid}/{eventdateid}")
    public Map<String, String> addParticipantToEventDate(@PathVariable("participantid") Integer participantId, @PathVariable("eventdateid") Integer eventDateId) {
        Map<String, String> returnMap = new HashMap<>();

        Optional<Participant> participantOptional = participantService.get(participantId);
        Optional<EventDate> eventDateOptional = eventDateService.get(eventDateId);
        if(participantOptional.isEmpty()) {
            this.logger.warn(String.format("Get-Request: Get participant with id: participant with id-%s doesn't exist", participantId));
            throw new RecordNotFoundException();
        }
        if(eventDateOptional.isEmpty()) {
            this.logger.warn(String.format("Get-Request: Get event-date with id: event-date with id-%s doesn't exist", eventDateId));
            throw new RecordNotFoundException();
        }

        try {
            participantEventService.addParticipantToEventDate(participantOptional.get(), eventDateOptional.get());

            this.logger.info("Added participant to event-date");

            returnMap.put("allowed", "true");
            returnMap.put("message", "Successfully");
        }catch(WarningException ex) {
            this.logger.info(ex.getMessage());

            returnMap.put("allowed", "false");
            returnMap.put("message", ex.getMessage());
        }

        return returnMap;
    }

    @PostMapping(path="removeDate/{participantid}/{eventdateid}")
    public Map<String, String> removeParticipantToEventDate(@PathVariable("participantid") Integer participantId, @PathVariable("eventdateid") Integer eventDateId) {
        Map<String, String> returnMap = new HashMap<>();

        Optional<Participant> participantOptional = participantService.get(participantId);
        Optional<EventDate> eventDateOptional = eventDateService.get(eventDateId);
        if(participantOptional.isEmpty()) {
            this.logger.warn(String.format("Get-Request: Get participant with id: participant with id-%s doesn't exist", participantId));
            throw new RecordNotFoundException();
        }
        if(eventDateOptional.isEmpty()) {
            this.logger.warn(String.format("Get-Request: Get event-date with id: event-date with id-%s doesn't exist", eventDateId));
            throw new RecordNotFoundException();
        }

        try {
            participantEventService.removeParticipantFromEventDate(participantOptional.get(), eventDateOptional.get());

            this.logger.info("Removed participant from event-date");

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
