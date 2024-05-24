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
    // Lege constructor
    public QRCodeImage() {
    }
    // Constructor met image parameter
    public QRCodeImage(byte[] image) {
        this.image = image;
    }

    // Getter voor qrCodeId
    public Long getQrCodeId() {
        return qrCodeId;
    }
    // Setter voor qrCodeId
    public void setQrCodeId(Long qrCodeId) {
        this.qrCodeId = qrCodeId;
    }
    // Getter voor image
    public byte[] getImage() {
        return image;
    }
    // Setter voor image
    public void setImage(byte[] image) {
        this.image = image;
    }
    // Getter voor ticket
    public Ticket getTicket() {
        return ticket;
    }
    // Setter voor ticket
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getContenType() {
        return getContenType();
    }
}