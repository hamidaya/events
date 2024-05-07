package org.music.events.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Table(name="events")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Event {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long eventId;
    private String eventName;
    private String eventType;
    private String eventLocation;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    private Double eventPrice;
    private Integer availableTickets;
    private String eventDescription;

@OneToMany(mappedBy = "event")

private List<Ticket> tickets;

    public Event() {
    }


    public Event(Long eventId, String eventName, String eventType, String eventLocation, LocalDate eventStartDate, LocalDate eventEndDate, Double eventPrice, Integer availableTickets, String eventDescription) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventLocation = eventLocation;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventPrice = eventPrice;
        this.eventDescription = eventDescription;
        this.availableTickets = availableTickets;
    }

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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
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

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}