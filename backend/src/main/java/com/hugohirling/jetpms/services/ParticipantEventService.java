package com.hugohirling.jetpms.services;

import com.hugohirling.jetpms.entities.EventDate;
import com.hugohirling.jetpms.entities.Participant;
import com.hugohirling.jetpms.exceptions.WarningException;
import com.hugohirling.jetpms.repositories.EventDateRepository;
import com.hugohirling.jetpms.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantEventService {

    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private EventDateRepository eventDateRepository;

    public void addParticipantToEventDate(final Participant participant, final EventDate eventDate) throws WarningException {
        if(eventDate.getParticipants().contains(participant) || participant.getAttendedDates().contains(eventDate)) {
            throw new WarningException("Participant is already registered in this event");
        }

        participant.addEventDate(eventDate);
        eventDate.addParticipant(participant);
        participantRepository.save(participant);
        eventDateRepository.save(eventDate);
    }

    public void removeParticipantFromEventDate(final Participant participant, final EventDate eventDate) throws WarningException{
        if(!eventDate.getParticipants().contains(participant) || !participant.getAttendedDates().contains(eventDate)) {
            throw new WarningException("Participant is not registered in this event");
        }

        participant.removeEventDate(eventDate);
        eventDate.removeParticipant(participant);
        participantRepository.save(participant);
        eventDateRepository.save(eventDate);
    }

}
