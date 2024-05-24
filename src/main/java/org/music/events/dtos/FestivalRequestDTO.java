package org.music.events.dtos;
import java.time.LocalDate;
public class FestivalRequestDTO extends EventRequestDTO {


    private String artistName;
    private Boolean campingAvailable;

    public FestivalRequestDTO(String eventName, String eventType, String eventLocation, LocalDate eventStartDate, LocalDate eventEndDate, Double eventPrice, Integer availableTickets, String eventDescription) {
        super(eventName, eventType, eventLocation, eventStartDate, eventEndDate, eventPrice, availableTickets, eventDescription);
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
