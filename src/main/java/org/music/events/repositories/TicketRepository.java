package org.music.events.repositories;
import org.music.events.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}

//    Later uitbreiden met sorteren
//    List<Ticket> findByUserId(int userId, Pageable pageable);
//    List<Ticket> findByUserId(int userId, Sort sort);
