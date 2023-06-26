package com.hugohirling.jetpms.controllers;

import com.hugohirling.jetpms.entities.EventDate;
import com.hugohirling.jetpms.entities.Participant;
import com.hugohirling.jetpms.exceptions.RecordNotFoundException;
import com.hugohirling.jetpms.responsemodels.FullEventDateResponse;
import com.hugohirling.jetpms.responsemodels.FullParticipantResponse;
import com.hugohirling.jetpms.services.EventDateService;
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
}
