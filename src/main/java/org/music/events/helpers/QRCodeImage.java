package org.music.events.helpers;

import jakarta.persistence.*;

@Entity

public class QRCodeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] image;

    public QRCodeImage(byte[] image, Long id) {
        this.image = image;
        this.id = id;
    }

    public QRCodeImage() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}