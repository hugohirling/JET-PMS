package com.hugohirling.jetpms.responsemodels;

import com.hugohirling.jetpms.entities.Compartment;

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
}
