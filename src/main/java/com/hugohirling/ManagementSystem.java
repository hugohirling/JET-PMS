package com.hugohirling;

import com.hugohirling.objects.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ManagementSystem {

    private final Connection connection;

    public ManagementSystem(final Connection connection) {
        this.connection = connection;
    }

    public void addParticipant(final Participant participant) throws SQLException {
        String SQL_INSERT = "INSERT INTO participants (first,last,dateOfBirth,localGroup";
        int counterQuestionmark = 4;
        if (participant.getPhoneNumber().isPresent()) {
            SQL_INSERT += ",phoneNumber";
            counterQuestionmark++;
        }
        if (participant.getEmail().isPresent()) {
            SQL_INSERT += ",email";
            counterQuestionmark++;
        }
        if (participant.getThreemaID().isPresent()) {
            SQL_INSERT += ",threemaID";
            counterQuestionmark++;
        }
        if (participant.getEmergencyPhoneNumber().isPresent()) {
            SQL_INSERT += ",emergencyPhoneNumber";
            counterQuestionmark++;
        }
        if (participant.getDrsa().isPresent()) {
            SQL_INSERT += ",drsa";
            counterQuestionmark++;
        }
        if (participant.getImageRight().isPresent()) {
            SQL_INSERT += ",imageRight";
            counterQuestionmark++;
        }
        if (participant.getNote().isPresent()) {
            SQL_INSERT += ",note";
            counterQuestionmark++;
        }
        SQL_INSERT += ") VALUES (?";
        for (int i = 1; i < counterQuestionmark; i++) {
            SQL_INSERT += ",?";
        }
        SQL_INSERT += ")";

        PreparedStatement ps = connection.prepareStatement(SQL_INSERT);

        int counter = 5;

        ps.setString(1, participant.getFirst());
        ps.setString(2, participant.getLast());
        ps.setDate(3, new java.sql.Date(participant.getDateOfBirth().getTime()));
        ps.setString(4, participant.getLocalGroup().name());
        if (participant.getPhoneNumber().isPresent()) {
            ps.setString(counter, participant.getPhoneNumber().get());
            counter++;
        }
        if (participant.getEmail().isPresent()) {
            ps.setString(counter, participant.getEmail().get());
            counter++;
        }
        if (participant.getThreemaID().isPresent()) {
            ps.setString(counter, participant.getThreemaID().get());
            counter++;
        }
        if (participant.getEmergencyPhoneNumber().isPresent()) {
            ps.setString(counter, participant.getEmergencyPhoneNumber().get());
            counter++;
        }
        if (participant.getDrsa().isPresent()) {
            ps.setString(counter, participant.getDrsa().get().name());
            counter++;
        }
        if (participant.getImageRight().isPresent()) {
            ps.setString(counter, participant.getImageRight().get().name());
            counter++;
        }
        if (participant.getNote().isPresent()) {
            ps.setString(counter, participant.getNote().get());
        }

        ps.executeUpdate();
    }

    public List<Participant> getAllParticipants() throws SQLException {

        String SQL_SELECT =
                "SELECT uid, first, last, dateOfBirth, localGroup, phoneNumber, email, threemaID, emergencyPhoneNumber, drsa, imageRight, note, attendedDates " +
                        "FROM participants";

        String SQL_SELECT_ASSIGNED =
                "SELECT epa.uid AS uid, epa.eid AS eid, shirt.size AS shirtsize, pants.size AS pantssize " +
                        "FROM equipment_participant_assigned AS epa " +
                        "LEFT OUTER JOIN equipment AS e " +
                        "ON epa.eid = e.eid " +
                        "LEFT OUTER JOIN shirt " +
                        "ON shirt.eid = e.eid " +
                        "LEFT OUTER JOIN pants " +
                        "ON pants.eid = e.eid";
        String SQL_SELECT_REQUESTED =
                "SELECT epr.uid AS uid, epr.eid AS eid, shirt.size AS shirtsize, pants.size AS pantssize " +
                        "FROM equipment_participant_requested AS epr " +
                        "LEFT OUTER JOIN equipment AS e " +
                        "ON epr.eid = e.eid " +
                        "LEFT OUTER JOIN shirt " +
                        "ON shirt.eid = e.eid " +
                        "LEFT OUTER JOIN pants " +
                        "ON pants.eid = e.eid";

        PreparedStatement psParticipants = connection.prepareStatement(SQL_SELECT);
        PreparedStatement psAssigned = connection.prepareStatement(SQL_SELECT_ASSIGNED);
        PreparedStatement psReqeuested = connection.prepareStatement(SQL_SELECT_REQUESTED);
        ResultSet rsParticipants = psParticipants.executeQuery();
        ResultSet rsAssigned = psAssigned.executeQuery();
        ResultSet rsRequested = psReqeuested.executeQuery();

        List<Participant> participants = new ArrayList<>();


        while(rsParticipants.next()) {
            int uid = rsParticipants.getInt("uid");

            String first = rsParticipants.getString("first");
            String last = rsParticipants.getString("last");
            Date dateOfBirth = rsParticipants.getDate("dateOfBirth");
            LocalGroup localGroup = LocalGroup.valueOf(rsParticipants.getString("localGroup"));
            Optional<String> phoneNumber = Optional.ofNullable(rsParticipants.getString("phoneNumber"));
            Optional<String> email = Optional.ofNullable(rsParticipants.getString("email"));
            Optional<String> threemaID = Optional.ofNullable(rsParticipants.getString("threemaID"));
            Optional<String> emergencyPhoneNumber = Optional.ofNullable(rsParticipants.getString("emergencyPhoneNumber"));
            Optional<DRSA> drsa = Optional.empty();
            String drsaString = rsParticipants.getString("drsa");
            if (!rsParticipants.wasNull()) drsa = Optional.of(DRSA.valueOf(drsaString));
            Optional<ImageRight> imageRight = Optional.empty();
            String imageRightString = rsParticipants.getString("imageRight");
            if (!rsParticipants.wasNull()) imageRight = Optional.of(ImageRight.valueOf(imageRightString));
            Optional<String> note = Optional.ofNullable(rsParticipants.getString("note"));
            Optional<String> attendedDates = Optional.ofNullable(rsParticipants.getString("attendedDates"));

            participants.add(new Participant(
                    uid,
                    first,
                    last,
                    dateOfBirth,
                    localGroup,
                    phoneNumber,
                    email,
                    threemaID,
                    emergencyPhoneNumber,
                    drsa,
                    imageRight,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    note,
                    new ArrayList<>()
            ));
        }

        addEquipmentToParticipant(participants, rsAssigned, Participant::addAssignedEquipment);
        addEquipmentToParticipant(participants, rsRequested, Participant::addRequestedEquipment);

        return participants;
    }

    private void addEquipmentToParticipant(final List<Participant> participantList, final ResultSet resultSet, final BiConsumer<Participant, Equipment> consumer) throws SQLException {
        while(resultSet.next()) {
            int eid = resultSet.getInt("eid");
            int uid = resultSet.getInt("uid");
            Optional<String> shirtsize = Optional.ofNullable(resultSet.getString("shirtsize"));
            Optional<String> pantssize = Optional.ofNullable(resultSet.getString("pantssize"));

            Optional<Equipment> equipment = Optional.empty();
            if (shirtsize.isPresent())
                equipment = Optional.of(new TShirt(eid, shirtsize.get()));
            else if (pantssize.isPresent())
                equipment = Optional.of(new Pants(eid, pantssize.get()));

            if(equipment.isPresent()) {
                Optional<Participant> participant = participantList.stream().filter(p -> p.getUid() == uid).findFirst();
                if(participant.isPresent())
                    consumer.accept(participant.get(), equipment.get());
            }

        }
    }
}
