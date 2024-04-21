package org.music.events.models;

import jakarta.persistence.*;

import java.time.LocalDate;
@Table(name = "festivals")
@Entity
public class Festival extends Event {

    String artistName;
    Boolean campingAvailable;



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
