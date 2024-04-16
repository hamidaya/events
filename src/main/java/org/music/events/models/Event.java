package org.music.events.models;



import jakarta.persistence.*;
import org.music.events.interfaces.IEevent;

import java.time.LocalDate;


@Table(name = "events")
@MappedSuperclass
public class Event implements IEevent {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventID;
    private String eventName;
    private String eventLocation;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    private Double eventPrice;
    private Integer availableTickets;

    public Event() {

    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
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

    public LocalDate getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(LocalDate eventEndDate) {
        this.eventEndDate = eventEndDate;
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

    public Event(Long eventID, String eventName, String eventLocation, LocalDate eventStartDate, LocalDate eventEndDate, Double eventPrice, Integer availableTickets) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventPrice = eventPrice;
        this.availableTickets = availableTickets;


    }

    @Override
    public void myEvent() {
        System.out.println("Event processing interface executed...");

    }
}

