package org.music.events.helpers;


import jakarta.persistence.GeneratedValue;

import java.util.UUID;

public class TicketAvailabilityManager {

    private long ticketId = Long.parseLong(UUID.randomUUID().toString());;
    private long eventId;


    public TicketAvailabilityManager(long ticketId, long eventId) {
        this.ticketId = ticketId;
        this.eventId = eventId;

    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }
}
