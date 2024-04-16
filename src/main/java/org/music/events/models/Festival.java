package org.music.events.models;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
public class Festival extends Event {

    private String festivalName;
    private String artistName;
    private Boolean campingAvailable;

    public Festival (String eventName, String eventLocation, LocalDate eventStartDate, LocalDate eventEndDate, Double eventPrice, Integer availableTickets, String festivalName, String artistName, Boolean campingAvailable) {
        super(null, eventName, eventLocation, eventStartDate, eventEndDate, eventPrice, availableTickets);
        this.festivalName = festivalName;
        this.artistName = artistName;
        this.campingAvailable = campingAvailable;
    }

    public Festival() {

    }



    public String getFestivalName() {
        return festivalName;
    }
    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
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