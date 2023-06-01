package com.hugohirling.objects;

public class Pants extends Equipment{
    private final String size;

    public Pants(final int eid, final Participant assignedTo, final String size) {
        super(eid, assignedTo);
        this.size =size;
    }

    public Pants(final int eid, final String size) {
        super(eid);
        this.size =size;
    }

    public String getSize() {
        return this.size;
    }
}
