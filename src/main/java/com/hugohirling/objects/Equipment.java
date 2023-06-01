package com.hugohirling.objects;

import java.util.Optional;

public abstract class Equipment {
    private final int eid;
    private Optional<Participant> assignedTo;

    public Equipment(final int eid, final Participant assignedTo) {
        this.eid = eid;
        this.assignedTo = Optional.of(assignedTo);
    }
    public Equipment(final int eid) {
        this.eid = eid;
        this.assignedTo = Optional.empty();
    }

    public int getEID() {
        return eid;
    }
    public Optional<Participant> getAssignedTo() {
        return assignedTo;
    }

    public void setParticipant(final Participant participant) {
        assignedTo = Optional.of(participant);
    }
}
