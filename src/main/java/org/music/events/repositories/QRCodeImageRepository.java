package org.music.events.repositories;
import org.music.events.models.QRCodeImage;
import org.music.events.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface QRCodeImageRepository extends JpaRepository<QRCodeImage, Long> {
     Optional<QRCodeImage> findByTicket(Ticket ticket);
}