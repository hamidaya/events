package org.music.events.dtos;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class eventRequestDTO {

    @NotBlank(message = "eventName must not be blank")
    private String eventName;
    @NotBlank(message = "festival must not be blank")
    private String festival;
    @NotBlank(message = "Party must not be blank")
    private String party;
    @NotBlank(message = "eventLocation must not be blank")
    private String eventLocation;
    @NotBlank(message = "eventStartDate must not be blank")
    private Date eventStartDate;
    @NotBlank(message = "eventPrice must not be blank")
    private Double eventPrice;
    @NotBlank(message = "availableTickets must not be blank")
    private Integer availableTickets;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getFestival() {
        return festival;
    }

    public void setFestival(String festival) {
        this.festival = festival;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
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








