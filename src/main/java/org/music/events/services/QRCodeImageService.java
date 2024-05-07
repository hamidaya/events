package org.music.events.services;

import org.music.events.helpers.QRCodeImage;
import org.music.events.repositories.QRCodeImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class QRCodeImageService {

    @Autowired
    private final QRCodeImageRepository qrCodeImageRepository;

    public QRCodeImageService(QRCodeImageRepository qrCodeImageRepository) {
        this.qrCodeImageRepository = qrCodeImageRepository;
    }

    @Transactional
    public QRCodeImage saveQRCodeImage(byte[] imageBytes) {
        QRCodeImage qrCodeImage = new QRCodeImage();
        qrCodeImage.setImage(imageBytes);
        return qrCodeImageRepository.save(qrCodeImage);
    }
}
