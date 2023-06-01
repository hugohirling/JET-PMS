package com.hugohirling.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Participant {

    private final int uid;
    private final String first;
    private final String last;
    private final Date dateOfBirth;
    private final LocalGroup localGroup;
    private final Optional<String> phoneNumber;
    private final Optional<String> email;
    private final Optional<String> threemaID;
    private final Optional<String> emergencyPhoneNumber;
    private final Optional<DRSA> drsa;
    private final Optional<ImageRight> imageRight;
    private final List<Equipment> assignedEquipment;
    private final List<Equipment> requestedEquipment;
    private final Optional<String> note;
    private final List<Date> attendedDates;

    public Participant(
            final int uid,
            final String first,
            final String last,
            final Date dateOfBirth,
            final LocalGroup localGroup,
            final String phoneNumber,
            final String email,
            final String threemaID,
            final String emergencyPhoneNumber,
            final DRSA drsa,
            final ImageRight imageRight,
            final List<Equipment> assignedEquipment,
            final List<Equipment> requestedEquipment,
            final String note,
            final List<Date> attendedDates) {
        this.uid = uid;
        this.first = first;
        this.last = last;
        this.dateOfBirth = dateOfBirth;
        this.localGroup = localGroup;
        this.phoneNumber = Optional.of(phoneNumber);
        this.email = Optional.of(email);
        this.threemaID = Optional.of(threemaID);
        this.emergencyPhoneNumber = Optional.of(emergencyPhoneNumber);
        this.drsa = Optional.of(drsa);
        this.imageRight = Optional.of(imageRight);
        this.assignedEquipment = new ArrayList<>();
        this.requestedEquipment = new ArrayList<>();
        this.note = Optional.of(note);
        this.attendedDates = attendedDates;

        for(final Equipment e : assignedEquipment) {
            this.addAssignedEquipment(e);
        }
        for(final Equipment e : requestedEquipment) {
            this.addRequestedEquipment(e);
        }
    }

    public Participant(
            final int uid,
            final String first,
            final String last,
            final Date dateOfBirth,
            final LocalGroup localGroup,
            final Optional<String> phoneNumber,
            final Optional<String> email,
            final Optional<String> threemaID,
            final Optional<String> emergencyPhoneNumber,
            final Optional<DRSA> drsa,
            final Optional<ImageRight> imageRight,
            final List<Equipment> assignedEquipment,
            final List<Equipment> requestedEquipment,
            final Optional<String> note,
            final List<Date> attendedDates) {
        this.uid = uid;
        this.first = first;
        this.last = last;
        this.dateOfBirth = dateOfBirth;
        this.localGroup = localGroup;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.threemaID = threemaID;
        this.emergencyPhoneNumber = emergencyPhoneNumber;
        this.drsa = drsa;
        this.imageRight = imageRight;
        this.assignedEquipment = new ArrayList<>();
        this.requestedEquipment = new ArrayList<>();
        this.note = note;
        this.attendedDates = attendedDates;

        for(final Equipment e : assignedEquipment) {
            this.addAssignedEquipment(e);
        }
        for(final Equipment e : requestedEquipment) {
            this.addRequestedEquipment(e);
        }
    }

    public Participant(
            final String first,
            final String last,
            final Date dateOfBirth,
            final LocalGroup localGroup,
            final String phoneNumber,
            final String email,
            final String threemaID,
            final String emergencyPhoneNumber,
            final ImageRight imageRight) {
        this.uid = -1;
        this.first = first;
        this.last = last;
        this.dateOfBirth = dateOfBirth;
        this.localGroup = localGroup;
        this.phoneNumber = Optional.of(phoneNumber);
        this.email = Optional.of(email);
        this.threemaID = Optional.of(threemaID);
        this.emergencyPhoneNumber = Optional.of(emergencyPhoneNumber);
        this.drsa = Optional.empty();
        this.imageRight = Optional.of(imageRight);
        this.assignedEquipment = new ArrayList<>();
        this.requestedEquipment = new ArrayList<>();
        this.note = Optional.empty();
        this.attendedDates = new ArrayList<>();
    }

    public int getUid() {
        return uid;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalGroup getLocalGroup() {
        return localGroup;
    }

    public Optional<String> getPhoneNumber() {
        return phoneNumber;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public Optional<String> getThreemaID() {
        return threemaID;
    }

    public Optional<String> getEmergencyPhoneNumber() {
        return emergencyPhoneNumber;
    }

    public Optional<DRSA> getDrsa() {
        return drsa;
    }

    public Optional<ImageRight> getImageRight() {
        return imageRight;
    }

    public List<Equipment> getAssignedEquipment() {
        return new ArrayList<>(assignedEquipment);
    }

    public List<Equipment> getRequestedEquipment() {
        return new ArrayList<>(requestedEquipment);
    }

    public Optional<String> getNote() {
        return note;
    }

    public List<Date> getAttendedDates() {
        return new ArrayList<>(attendedDates);
    }

    public void addAssignedEquipment(final Equipment equipment) {
        this.assignedEquipment.add(equipment);
        if(equipment.getAssignedTo().isEmpty()) {
            equipment.setParticipant(this);
        }
    }

    public void addRequestedEquipment(final Equipment equipment) {
        this.requestedEquipment.add(equipment);
        if(equipment.getAssignedTo().isEmpty()) {
            equipment.setParticipant(this);
        }
    }
}
