package org.music.events.dtos;
import java.time.LocalDate;

public class PartyRequestDTO extends EventRequestDTO {


    private String djName;
    private String dressCode;

    public PartyRequestDTO(String eventName, String eventType, String eventLocation, LocalDate eventStartDate, LocalDate eventEndDate, Double eventPrice, Integer availableTickets, String eventDescription, String djName, String dressCode) {
        super(eventName, eventType, eventLocation, eventStartDate, eventEndDate, eventPrice, availableTickets, eventDescription);
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