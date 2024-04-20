package org.music.events.dtos;
import java.time.LocalDate;
public class FestivalRespondsDTO extends EventRespondsDTO {

    private String artistName;
    private Boolean campingAvailable;

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

