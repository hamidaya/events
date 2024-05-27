package org.music.events.services;


import org.music.events.repositories.QRCodeImageRepository;
import org.music.events.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QRCodeImageService {

    private final QRCodeImageRepository qrCodeImageRepository;
    public final TicketService saveQRCodeImage;
//    private final UserRepository userRepository;


    @Autowired
    public QRCodeImageService(QRCodeImageRepository qrCodeImageRepository, TicketService saveQRCodeImage, UserRepository userRepository) {
        this.qrCodeImageRepository = qrCodeImageRepository;
        this.saveQRCodeImage = saveQRCodeImage;
//        this.userRepository = userRepository;
    }

//    @Transactional
//    public QRCodeImage saveQRCodeImage(byte[] imageBytes) {
//        QRCodeImage qrCodeImage = new QRCodeImage();
//        qrCodeImage.setImage(imageBytes);
//        return qrCodeImageRepository.save(qrCodeImage);
//    }




    }
