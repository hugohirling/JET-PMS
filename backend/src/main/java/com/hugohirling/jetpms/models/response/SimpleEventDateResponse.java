package com.hugohirling.jetpms.models.response;

import com.hugohirling.jetpms.entities.Compartment;
import com.hugohirling.jetpms.entities.EventDate;

import java.util.Date;

public class SimpleEventDateResponse {

    private final Integer evid;
    private final Date date;
    private final Compartment compartment;
    private final String note;

    public SimpleEventDateResponse(Integer evid, Date date, Compartment compartment, String note) {
        this.evid = evid;
        this.date = date;
        this.compartment = compartment;
        this.note = note;
    }

    public Integer getEvid() {
        return evid;
    }

    public Date getDate() {
        return date;
    }

    public Compartment getCompartment() {
        return compartment;
    }

    public String getNote() {
        return note;
    }

    public static SimpleEventDateResponse getFromEventDate(final EventDate eventDate) {
        return new SimpleEventDateResponse(
                eventDate.getEvid(),
                eventDate.getDate(),
                eventDate.getCompartment(),
                eventDate.getNotes()
        );
    }
}
