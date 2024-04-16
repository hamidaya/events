package org.music.events.models;

import jakarta.persistence.*;

import java.util.Date;

@Table(name = "partys")

public class Party extends Event {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long partyID;
    String partyName;
    Date partyDate;
    String djName;
    String dressCode;

    public Party(Long partyID, String partyName, Date partyDate, String djName, String dressCode) {
        this.partyID = partyID;
        this.partyName = partyName;
        this.partyDate = partyDate;
        this.djName = djName;
        this.dressCode = dressCode;
    }

    public Party(Long eventID, String eventName, String eventLocation, Date eventStartDate, Double eventPrice, Integer availableTickets, Long partyID, String partyName, Date partyDate, String djName, String dressCode) {
        super(eventID, eventName, eventLocation, eventStartDate, eventPrice, availableTickets);
        this.partyID = partyID;
        this.partyName = partyName;
        this.partyDate = partyDate;
        this.djName = djName;
        this.dressCode = dressCode;
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

    public Date getPartyDate() {
        return partyDate;
    }

    public void setPartyDate(Date partyDate) {
        this.partyDate = partyDate;
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