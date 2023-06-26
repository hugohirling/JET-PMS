package com.hugohirling.jetpms.responsemodels;

import com.hugohirling.jetpms.entities.DRSA;
import com.hugohirling.jetpms.entities.ImageRight;
import com.hugohirling.jetpms.entities.LocalGroup;

import java.util.Date;
import java.util.List;

public class SimpleParticipantResponse {

    private final Integer uid;
    private final String first;
    private final String last;
    private final Date dateOfBirth;
    private final LocalGroup localGroup;
    private final String phoneNumber;
    private final String email;
    private final String threemaID;
    private final String emergencyPhoneNumber;
    private final DRSA drsa;
    private final ImageRight imageRight;
    private final String note;

    public SimpleParticipantResponse(
            final Integer uid,
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
            final String note) {
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
        this.note = note;
    }

    public Integer getUid() {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getThreemaID() {
        return threemaID;
    }

    public String getEmergencyPhoneNumber() {
        return emergencyPhoneNumber;
    }

    public DRSA getDrsa() {
        return drsa;
    }

    public ImageRight getImageRight() {
        return imageRight;
    }

    public String getNote() {
        return note;
    }

}
