package org.music.events.dtos;

public class TicketRequestDTO {


    private Long eventId;
    private String eventName;
    private Long availableTickets;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(Long availableTickets) {
        this.availableTickets = availableTickets;
    }

    public TicketRequestDTO(Long eventId, String eventName, Long availableTickets) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.availableTickets = availableTickets;


    }
}



