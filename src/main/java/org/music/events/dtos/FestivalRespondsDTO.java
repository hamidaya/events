package org.music.events.dtos;
import java.time.LocalDate;
public class FestivalRespondsDTO extends EventRespondsDTO {

    private String artistName;
    private Boolean campingAvailable;


    public FestivalRespondsDTO() {

    }

    public FestivalRespondsDTO(Long eventId, String eventType, String eventName, String eventLocation, LocalDate eventStartDate, LocalDate eventEndDate, Double eventPrice, Integer availableTickets, String eventDescription, String artistName, Boolean campingAvailable) {
        super(eventId, eventType, eventName, eventLocation, eventStartDate, eventEndDate, eventPrice, availableTickets, eventDescription);
        this.artistName = artistName;
        this.campingAvailable = campingAvailable;
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

