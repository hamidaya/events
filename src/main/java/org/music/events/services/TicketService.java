package org.music.events.services;
import org.hibernate.event.service.spi.EventListenerRegistrationException;
import org.music.events.exceptions.UsernameNotFoundException;
import org.music.events.models.TicketStatus;
import org.music.events.repositories.EventRepository;
import org.music.events.repositories.TicketRepository;
import org.music.events.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.music.events.models.Ticket;
import org.w3c.dom.events.EventException;


@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    private UserRepository userRepository;
    private EventRepository eventRepository;

        public void purchaseTicket(String eventId, String username) {
        // Create a new Ticket object
        Ticket ticket = new Ticket();
        // Set the event and user for the ticket
        // You may need to fetch the event and user objects from their respective repositories
        ticket.setEvent(eventRepository.findEventByEventName(eventId).orElseThrow(() -> new EventListenerRegistrationException("Event not found")));
        ticket.setUser(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found")));
        // Set any other relevant information for the ticket
        ticket.setStatus(TicketStatus.VALID);
        // Save the ticket to the database
        ticketRepository.save(ticket);
    }

    public String validateTicket(String qrCode) {
        Ticket ticket = ticketRepository.findByQrCode(qrCode);
        if (ticket != null && ticket.getStatus() == TicketStatus.VALID) {
            ticket.setStatus(TicketStatus.USED);
            ticketRepository.save(ticket);
            return "Ticket is valid!";
        } else {
            return "Invalid ticket!";
        }
    }

}