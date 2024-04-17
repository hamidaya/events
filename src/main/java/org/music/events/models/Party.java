package org.music.events.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name="partys")
@Entity
public class Party extends Event {

    String djName;
    String dressCode;

    public Party() {;
    }

    public Party(String partyName, String djName, String dressCode) {
        this.djName = djName;
        this.dressCode = dressCode;
    }

    public Party(Long eventId, String eventName, String eventType, String eventLocation, LocalDate eventStartDate, LocalDate eventEndDate, Double eventPrice, Integer availableTickets, String partyName, String djName, String dressCode) {
        super(eventId, eventName, eventType, eventLocation, eventStartDate, eventEndDate, eventPrice, availableTickets);
        this.djName = djName;
        this.dressCode = dressCode;
    }


    public String getDjName() {
        return djName;
    }

    public void setDjName(String djName) {
        this.djName = djName;
    }

    public String getDressCode() {
        return dressCode;
    }

    public void setDressCode(String dressCode) {
        this.dressCode = dressCode;
    }
}

