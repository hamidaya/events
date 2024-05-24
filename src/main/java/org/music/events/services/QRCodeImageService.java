package org.music.events.services;

import org.music.events.models.QRCodeImage;
import org.music.events.repositories.QRCodeImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class QRCodeImageService {

    private final QRCodeImageRepository qrCodeImageRepository;
    public final TicketService saveQRCodeImage;

    @Autowired
    public QRCodeImageService(QRCodeImageRepository qrCodeImageRepository, TicketService saveQRCodeImage) {
        this.qrCodeImageRepository = qrCodeImageRepository;
        this.saveQRCodeImage = saveQRCodeImage;
    }

    @Transactional
    public QRCodeImage saveQRCodeImage(byte[] imageBytes) {
        QRCodeImage qrCodeImage = new QRCodeImage();
        qrCodeImage.setImage(imageBytes);
        return qrCodeImageRepository.save(qrCodeImage);
    }
    }
