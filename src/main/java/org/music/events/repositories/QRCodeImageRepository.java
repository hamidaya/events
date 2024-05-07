package org.music.events.repositories;

import org.music.events.helpers.QRCodeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QRCodeImageRepository extends JpaRepository< QRCodeImage, String> {


}