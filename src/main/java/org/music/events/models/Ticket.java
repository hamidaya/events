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
    @JoinColumn(name = "eventId", referencedColumnName = "eventId")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
    private QRCodeImage qrCodeImage;

    @OneToOne
    @JoinColumn(name = "profileId")
    private Profile profile;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public QRCodeImage getQrCodeImage() {
        return qrCodeImage;
    }

    public void setQrCodeImage(QRCodeImage qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
    }

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

    public Long getTicketId() {
        return ticketId;
    }
    public void setTicketId(Long ticketId) {
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


