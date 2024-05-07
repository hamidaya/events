package org.music.events.models;
import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    private String qrCode;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public Ticket() {

    }

    public TicketStatus getStatus() {
        return status;
    }
    public void setStatus(TicketStatus status) {
        this.status = status;
    }
    public Long getId() {
        return ticketId;
    }
    public void setId(Long ticketId) {
        this.ticketId = ticketId;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }


    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;


    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Ticket(String qrCode) {
        this.qrCode = qrCode;


    }

    public void setUser(Object userNotFound) {

    }
}


