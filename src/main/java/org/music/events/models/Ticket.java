package org.music.events.models;
import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

//    public QRCodeImage getQrCodeImage() {
//        return qrCodeImage;
//    }

    private String qrCode;
    @ManyToOne
    @JoinColumn(name = "eventId", referencedColumnName = "eventId")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

//    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
//    private QRCodeImage qrCodeImage;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


//    public void setQrCodeImage(QRCodeImage qrCodeImage) {
//        this.qrCodeImage = qrCodeImage;
//    }

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

    public String getQrCode() {
        return qrCode;
    }

    public void setUser(Object userNotFound) {

    }

    public void setUsername(String username) {
    }

    public void setName(String name) {
    }

  }


