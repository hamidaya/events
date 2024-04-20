package org.music.events.models;
import jakarta.persistence.*;
import java.time.LocalDate;
@Table(name="festivals")
@Entity
public class Festival extends Event {

    private String artistName;
    private Boolean campingAvailable;


    public Festival(String artistName, Boolean campingAvailable) {
        this.artistName = artistName;
        this.campingAvailable = campingAvailable;
    }


    public Festival(Long eventId, String eventName, String eventType, String eventLocation, LocalDate eventStartDate, LocalDate eventEndDate, Double eventPrice, Integer availableTickets, String eventDescription, String artistName, Boolean campingAvailable) {
        super(eventId, eventName, eventType, eventLocation, eventStartDate, eventEndDate, eventPrice, availableTickets, eventDescription);
        this.artistName = artistName;
        this.campingAvailable = campingAvailable;
    }

    public Festival() {

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