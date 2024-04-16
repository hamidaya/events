package org.music.events.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Party extends Event {

    String partyName;
    String djName;
    String dressCode;


    public Party() {;
    }

    public Party(Long partyID, String partyName, String djName, String dressCode) {
        this.partyName = partyName;
        this.djName = djName;
        this.dressCode = dressCode;
    }

    public Party(Long eventID, String eventName, String eventLocation, LocalDate eventStartDate, LocalDate eventEndDate, Double eventPrice, Integer availableTickets, Long partyID, String partyName, String djName, String dressCode) {
        super(eventID, eventName, eventLocation, eventStartDate, eventEndDate, eventPrice, availableTickets);
        this.partyName = partyName;
        this.djName = djName;
        this.dressCode = dressCode;
    }
}

