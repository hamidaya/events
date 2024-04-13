package org.music.events.models;

import java.util.Date;

public class Party extends Event {

    Long partyID;
    Date partyDate;
    String djName;
    String dressCode;

    public Party(Long eventID, String festival, String party, String eventName, String eventLocation, Date eventStartDate, Double eventPrice, Integer availableTickets, Long partyID, Date partyDate, String djName, String dressCode) {
        super(eventID, festival, party, eventName, eventLocation, eventStartDate, eventPrice, availableTickets);
        this.partyID = partyID;
        this.partyDate = partyDate;
        this.djName = djName;
        this.dressCode = dressCode;
    }

    public Party(Long partyID, Date partyDate, String djName, String dressCode) {
        this.partyID = partyID;
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


