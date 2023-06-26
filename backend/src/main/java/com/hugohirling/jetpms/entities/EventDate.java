package com.hugohirling.jetpms.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class EventDate {

    @Id
    @GeneratedValue
    private Integer evid;

    private Date date;
    @ManyToMany(mappedBy = "attendedDates")
    private List<Participant> participants;
    @Enumerated(EnumType.STRING)
    private Compartment compartment;
    private String notes;




    public Integer getEvid() {
        return evid;
    }

    public void setEvid(Integer evid) {
        this.evid = evid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public Compartment getCompartment() {
        return compartment;
    }

    public void setCompartment(Compartment compartment) {
        this.compartment = compartment;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void addParticipant(final Participant participant) {
        this.participants.add(participant);
    }
    public void removeParticipant(final Participant participant) {
        this.participants.remove(participant);
    }
}
