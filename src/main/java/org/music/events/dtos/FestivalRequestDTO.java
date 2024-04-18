package org.music.events.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class FestivalRequestDTO {

    @NotNull(message = "festivalName must not be blank")
    private String eventName;
    //  @NotNull(message = "festivalLocation must not be blank")
    private String eventType;
    private String eventLocation;
    // @NotBlank(message = "festivalStartDate must not be blank")
    private LocalDate eventStartDate;
    // @NotBlank(message = "festivalPrice must not be blank")
    private LocalDate eventEndDate;
    private Double eventPrice;
    // @NotBlank(message = "availableTickets must not be blank")
    private Integer availableTickets;

    private String eventDescription;

    private String artistName;

    private Boolean campingAvailable;

    public FestivalRequestDTO(String eventName, String eventType, String eventLocation, LocalDate eventStartDate, LocalDate eventEndDate, Double eventPrice, Integer availableTickets, String eventDescription, String artistName, Boolean campingAvailable) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventLocation = eventLocation;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventPrice = eventPrice;
        this.availableTickets = availableTickets;
        this.eventDescription = eventDescription;
        this.artistName = artistName;
        this.campingAvailable = campingAvailable;
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

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Boolean getCampingAvailable() {
        return campingAvailable;
    }

    public void setCampingAvailable(Boolean campingAvailable) {
        this.campingAvailable = campingAvailable;
    }
}