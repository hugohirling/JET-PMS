package com.hugohirling.jetpms.responsemodels;

import com.hugohirling.jetpms.entities.*;


import java.util.Date;
import java.util.List;

public class FullParticipantResponse extends SimpleParticipantResponse{

    private final List<SimpleEquipmentResponse> assignedEquipment;
    private final List<SimpleEventDateResponse> attendedDates;

    public FullParticipantResponse(Integer uid, String first, String last, Date dateOfBirth, LocalGroup localGroup, String phoneNumber, String email, String threemaID, String emergencyPhoneNumber, DRSA drsa, ImageRight imageRight, List<SimpleEquipmentResponse> assignedEquipment, String note, List<SimpleEventDateResponse> attendedDates) {
        super(uid, first, last, dateOfBirth, localGroup, phoneNumber, email, threemaID, emergencyPhoneNumber, drsa, imageRight, note);
        this.assignedEquipment = assignedEquipment;
        this.attendedDates = attendedDates;
    }

    public List<SimpleEquipmentResponse> getAssignedEquipment() {
        return assignedEquipment;
    }

    public List<SimpleEventDateResponse> getAttendedDates() {
        return attendedDates;
    }

    public static FullParticipantResponse getFromParticipant(final Participant participant) {
        return new FullParticipantResponse(
                participant.getUid(),
                participant.getFirst(),
                participant.getLast(),
                participant.getDateOfBirth(),
                participant.getLocalGroup(),
                participant.getPhoneNumber().orElse(null),
                participant.getEmail().orElse(null),
                participant.getThreemaID().orElse(null),
                participant.getEmergencyPhoneNumber().orElse(null),
                participant.getDrsa().orElse(null),
                participant.getImageRight().orElse(null),
                participant.getAssignedEquipment().stream().map(SimpleEquipmentResponse::getFromEquipment).toList(),
                participant.getNote().orElse(null),
                participant.getAttendedDates().stream().map(SimpleEventDateResponse::getFromEventDate).toList()
        );
    }
}
