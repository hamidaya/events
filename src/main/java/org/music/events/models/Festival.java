package org.music.events.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "events")
public class Festival extends Event {

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long festivalID;
    String festivalName;
    String artistName;
    Boolean campingAvailable;


    public Festival(Long festivalID, String festivalName, String artistName, Boolean campingAvailable) {
        this.festivalID = festivalID;
        this.festivalName = festivalName;
        this.artistName = artistName;
        this.campingAvailable = campingAvailable;

    }

    public Festival() {

    }

    public Long getFestivalID() {
        return festivalID;
    }

    public void setFestivalID(Long festivalID) {
        this.festivalID = festivalID;
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


    public Festival(Long eventID, String eventName, String eventLocation, LocalDate eventStartDate, LocalDate eventEndDate, Double eventPrice, Integer availableTickets, Long festivalID, String festivalName, String artistName, Boolean campingAvailable, LocalDate festivalStartDate, LocalDate festivalEndtDate) {
        super(eventID, eventName, eventLocation, eventStartDate, eventEndDate, eventPrice, availableTickets);
        this.festivalID = festivalID;
        this.festivalName = festivalName;
        this.artistName = artistName;
        this.campingAvailable = campingAvailable;
     }
}
