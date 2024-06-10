package org.music.events.dtos;
import java.time.LocalDate;
public class FestivalRequestDTO extends EventRequestDTO {

    private String artistName;
    private Boolean campingAvailable;

    public FestivalRequestDTO(String eventName, String eventType, String eventLocation, LocalDate eventStartDate, LocalDate eventEndDate, Double eventPrice, Integer availableTickets, String eventDescription, String artistName, Boolean campingAvailable) {
        super(eventName, eventType, eventLocation, eventStartDate, eventEndDate, eventPrice, availableTickets, eventDescription);
        this.artistName = artistName;
        this.campingAvailable = campingAvailable;
    }

    public FestivalRequestDTO(String artistName, Boolean campingAvailable) {
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
