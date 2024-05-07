package org.music.events.repositories;
import org.music.events.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByQrCode (String qrCode);
}
