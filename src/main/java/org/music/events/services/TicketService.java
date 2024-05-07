package org.music.events.services;

import org.music.events.exceptions.UsernameNotFoundException;
import org.music.events.models.Ticket;
import org.music.events.models.TicketStatus;
import org.music.events.repositories.EventRepository;
import org.music.events.repositories.TicketRepository;
import org.music.events.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public void purchaseTicket(Long eventId, String username) {
        // Create a new Ticket object
        Ticket ticket = new Ticket();

        // Set the event and user for the ticket
        ticket.setEvent(eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found")));

        ticket.setUser(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));

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
