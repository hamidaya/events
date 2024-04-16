package org.music.events.models;

import jakarta.persistence.*;

import java.util.Date;

@Table(name = "events")

public class Festival extends Event {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long festivalID;
    String festivalName;
    String artistName;
    Boolean campingAvailable;
    Date festivalStartDate;
    Date festivalEndtDate;

      public Festival(Long festivalID, String festivalName, String artistName, Boolean campingAvailable, Date festivalStartDate, Date festivalEndtDate) {
        this.festivalID = festivalID;
        this.festivalName = festivalName;
        this.artistName = artistName;
        this.campingAvailable = campingAvailable;
        this.festivalStartDate = festivalStartDate;
        this.festivalEndtDate = festivalEndtDate;
    }

    public Festival(Long eventID, String eventName, String eventLocation, Date eventStartDate, Double eventPrice, Integer availableTickets, Long festivalID, String festivalName, String artistName, Boolean campingAvailable, Date festivalStartDate, Date festivalEndtDate) {
        super(eventID, eventName, eventLocation, eventStartDate, eventPrice, availableTickets);
        this.festivalID = festivalID;
        this.festivalName = festivalName;
        this.artistName = artistName;
        this.campingAvailable = campingAvailable;
        this.festivalStartDate = festivalStartDate;
        this.festivalEndtDate = festivalEndtDate;
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

    public Date getFestivalStartDate() {
        return festivalStartDate;
    }

    public void setFestivalStartDate(Date festivalStartDate) {
        this.festivalStartDate = festivalStartDate;
    }

    public Date getFestivalEndtDate() {
        return festivalEndtDate;
    }

    public void setFestivalEndtDate(Date festivalEndtDate) {
        this.festivalEndtDate = festivalEndtDate;
    }
}