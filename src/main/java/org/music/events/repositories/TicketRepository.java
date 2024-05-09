package org.music.events.repositories;
import org.music.events.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
//    Ticket  (String qrCode);
    Ticket findByQrCode(String qrCode);
}