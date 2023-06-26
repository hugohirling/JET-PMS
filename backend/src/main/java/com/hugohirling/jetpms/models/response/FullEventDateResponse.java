package com.hugohirling.jetpms.models.response;

import com.hugohirling.jetpms.entities.Compartment;
import com.hugohirling.jetpms.entities.EventDate;

import java.util.Date;
import java.util.List;

public class FullEventDateResponse extends SimpleEventDateResponse{

    private final List<SimpleParticipantResponse> participants;

    public FullEventDateResponse(Integer evid, Date date, Compartment compartment, String note, List<SimpleParticipantResponse> participants) {
        super(evid, date, compartment, note);
        this.participants = participants;
    }

    public List<SimpleParticipantResponse> getParticipants() {
        return participants;
    }

    public static FullEventDateResponse getFromEventDate(final EventDate eventDate) {
        return new FullEventDateResponse(
                eventDate.getEvid(),
                eventDate.getDate(),
                eventDate.getCompartment(),
                eventDate.getNotes(),
                eventDate.getParticipants().stream().map(SimpleParticipantResponse::getFromParticipant).toList()
        );
    }
}
