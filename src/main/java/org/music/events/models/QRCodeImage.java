package org.music.events.models;
import jakarta.persistence.*;

@Entity
public class QRCodeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qrCodeId;
    @Lob
    private byte[] image;

    @OneToOne
    @JoinColumn(name = "ticketId", referencedColumnName = "ticketId")

    private Ticket ticket;
    public QRCodeImage() {
    }
    public QRCodeImage(byte[] image) {
        this.image = image;
    }
    public Long getQrCodeId() {
        return qrCodeId;
    }
    public void setQrCodeId(Long qrCodeId) {
        this.qrCodeId = qrCodeId;
    }
    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}