package com.hugohirling.jetpms.controllers;

import com.hugohirling.jetpms.entities.EventDate;
import com.hugohirling.jetpms.entities.Participant;
import com.hugohirling.jetpms.exceptions.RecordNotFoundException;
import com.hugohirling.jetpms.exceptions.WarningException;
import com.hugohirling.jetpms.models.response.FullEventDateResponse;
import com.hugohirling.jetpms.services.EventDateService;
import com.hugohirling.jetpms.services.ParticipantEventService;
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
@RequestMapping(path="/api/event")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EventDateController {

    @Autowired
    private EventDateService eventDateService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private ParticipantEventService participantEventService;

    private final Logger logger = LoggerFactory.getLogger(EventDateController.class);

    @GetMapping()
    public List<FullEventDateResponse> getEventDates() {
        return eventDateService.getAll().stream().map(FullEventDateResponse::getFromEventDate).toList();
    }

    @GetMapping("/{eventdateid}")
    public FullEventDateResponse getEventDateById(@PathVariable("eventdateid") Integer eventDateId) {
        Optional<EventDate> optional = eventDateService.get(eventDateId);
        if(optional.isEmpty()) {
            this.logger.warn(String.format("Get-Request: Get event-date with id: event-date with id-%s doesn't exist", eventDateId));
            throw new RecordNotFoundException();
        }
        return FullEventDateResponse.getFromEventDate(optional.get());
    }

    @PostMapping(path="/add")
    public Map<String, String> addNewEventDate(@RequestBody final EventDate eventDate) {
        Map<String, String> returnMap = new HashMap<>();

        this.logger.info("Added new event-date: " + eventDate.getDate() + " compartment: " + eventDate.getCompartment());

        returnMap.put("allowed", "true");
        returnMap.put("message", "Successfully");
        eventDateService.addNew(eventDate);

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
