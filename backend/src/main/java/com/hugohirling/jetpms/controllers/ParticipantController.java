package com.hugohirling.jetpms.controllers;

import com.hugohirling.jetpms.entities.Participant;
import com.hugohirling.jetpms.exceptions.RecordNotFoundException;
import com.hugohirling.jetpms.responsemodels.FullParticipantResponse;
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
@RequestMapping(path="/api/parti")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

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
}
