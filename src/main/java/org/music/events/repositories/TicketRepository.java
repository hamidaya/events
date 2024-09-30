package org.music.events.repositories;
import org.music.events.models.QRCodeImage;
import org.music.events.models.Ticket;
import org.music.events.models.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByTicketIdAndStatus(Long ticketId, TicketStatus status);
}