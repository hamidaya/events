package org.music.events.repositories;

import org.music.events.models.QRCodeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QRCodeImageRepository extends JpaRepository< QRCodeImage, String> {
     List<QRCodeImage> findQRCodeImageByQrCodeId(Long qrCodeId);

}