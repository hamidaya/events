package org.music.events.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class EventRequestDTO {

    @NotBlank(message = "eventID must not be blank")
    private Long eventID;
    @NotBlank(message = "eventName must not be blank")
    private String eventName;
    @NotBlank(message = "eventLocation must not be blank")
    private String eventLocation;
    @NotBlank(message = "eventStartDate must not be blank")
    private Date eventStartDate;
    @NotBlank(message = "eventPrice must not be blank")
    private Double eventPrice;
    @NotBlank(message = "availableTickets must not be blank")
    private Integer availableTickets;

    public EventRequestDTO(String eventName, String eventLocation, Date eventStartDate, Double eventPrice, Integer availableTickets) {
        this.eventName = eventName;
          this.eventLocation = eventLocation;
        this.eventStartDate = eventStartDate;
        this.eventPrice = eventPrice;
        this.availableTickets = availableTickets;
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

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate) {
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







