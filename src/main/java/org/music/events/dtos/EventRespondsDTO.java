package org.music.events.dtos;

import java.time.LocalDate;

public class EventRespondsDTO {

    private Long eventID;
    private String eventType;
    private String eventName;
    private String eventLocation;
    private LocalDate eventStartDate;
    private Double eventPrice;
    private Integer availableTickets;

    public EventRespondsDTO(Long eventID, String eventType, String eventName, String eventLocation, LocalDate eventStartDate, Double eventPrice, Integer availableTickets) {
        this.eventID = eventID;
        this.eventType = eventType;
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventStartDate = eventStartDate;
        this.eventPrice = eventPrice;
        this.availableTickets = availableTickets;
    }

    public EventRespondsDTO() {

    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public LocalDate getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(LocalDate eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public Double getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(Double eventPrice) {
        this.eventPrice = eventPrice;
    }

    public Integer getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(Integer availableTickets) {
        this.availableTickets = availableTickets;
    }
}

