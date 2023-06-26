package com.hugohirling.jetpms.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
public class Participant {

    @Id
    @GeneratedValue
    private Integer uid;

    private String first;
    private String last;
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    private LocalGroup localGroup;
    private String phoneNumber;
    private String email;
    private String threemaID;
    private String emergencyPhoneNumber;
    @Enumerated(EnumType.STRING)
    private DRSA drsa;
    @Enumerated(EnumType.STRING)
    private ImageRight imageRight;

    @OneToMany(mappedBy = "assignedTo")
    private List<Equipment> assignedEquipment;
    private String note;
    @ManyToMany
    @JoinTable(
            name="participants_events",
            joinColumns = @JoinColumn(name="uid"),
            inverseJoinColumns = @JoinColumn(name="evid")
    )
    private List<EventDate> attendedDates;

    public Participant() {
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalGroup getLocalGroup() {
        return localGroup;
    }

    public void setLocalGroup(LocalGroup localGroup) {
        this.localGroup = localGroup;
    }

    public Optional<String> getPhoneNumber() {
        return Optional.ofNullable(phoneNumber);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Optional<String> getThreemaID() {
        return Optional.ofNullable(threemaID);
    }

    public void setThreemaID(String threemaID) {
        this.threemaID = threemaID;
    }

    public Optional<String> getEmergencyPhoneNumber() {
        return Optional.ofNullable(emergencyPhoneNumber);
    }

    public void setEmergencyPhoneNumber(String emergencyPhoneNumber) {
        this.emergencyPhoneNumber = emergencyPhoneNumber;
    }

    public Optional<DRSA> getDrsa() {
        return Optional.ofNullable(drsa);
    }

    public void setDrsa(DRSA drsa) {
        this.drsa = drsa;
    }

    public Optional<ImageRight> getImageRight() {
        return Optional.ofNullable(imageRight);
    }

    public void setImageRight(ImageRight imageRight) {
        this.imageRight = imageRight;
    }

    public List<Equipment> getAssignedEquipment() {
        return assignedEquipment;
    }

    public void setAssignedEquipment(List<Equipment> assignedEquipment) {
        this.assignedEquipment = assignedEquipment;
    }

    public Optional<String> getNote() {
        return Optional.ofNullable(note);
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<EventDate> getAttendedDates() {
        return attendedDates;
    }

    public void setAttendedDates(List<EventDate> attendedDates) {
        this.attendedDates = attendedDates;
    }

    public void addEventDate(final EventDate eventDate) {
        this.attendedDates.add(eventDate);
    }
    public void removeEventDate(final EventDate eventDate) {
        this.attendedDates.remove(eventDate);
    }
    public void addEquipment(final Equipment equipment) {
        this.assignedEquipment.add(equipment);
    }
    public void removeEquipment(final Equipment equipment) {
        this.assignedEquipment.remove(equipment);
    }
}
