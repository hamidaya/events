package org.music.events.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Table(name = "partys")
public class Party extends Event {

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long partyID;
    String partyName;
    String djName;
    String dressCode;

    public Party(Long partyID, String partyName, Date partyDate, String djName, String dressCode) {
        this.partyID = partyID;
        this.partyName = partyName;
        this.djName = djName;
        this.dressCode = dressCode;
    }

    public Party() {

    }

    public Long getPartyID() {
        return partyID;
    }

    public void setPartyID(Long partyID) {
        this.partyID = partyID;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
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

    public Party(Long eventID, String eventName, String eventLocation, LocalDate eventStartDate, LocalDate eventEndDate, Double eventPrice, Integer availableTickets, Long partyID, String partyName, String djName, String dressCode) {
        super(eventID, eventName, eventLocation, eventStartDate, eventEndDate, eventPrice, availableTickets);
        this.partyID = partyID;
        this.partyName = partyName;

        this.djName = djName;
        this.dressCode = dressCode;
    }


}