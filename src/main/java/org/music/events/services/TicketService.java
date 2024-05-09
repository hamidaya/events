package org.music.events.services;
import org.hibernate.event.service.spi.EventListenerRegistrationException;
import org.music.events.models.*;
import org.music.events.repositories.EventRepository;
import org.music.events.repositories.TicketRepository;
import org.music.events.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    private UserRepository userRepository;
    private EventRepository eventRepository;

    public TicketService(UserRepository userRepository, EventRepository eventRepository, QRCodeImageService qrCodeImageService) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public void purchaseTicket(Long eventId, String username) {
        //kopen nieuwe ticket:
        Ticket ticket = new Ticket();
        ticket.setEvent(eventRepository.findById(eventId).orElseThrow(() -> new EventListenerRegistrationException("Event not found")));
        User user = (User) userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Gebruiker niet gevonden"));
        ticket.setUser(user); // Koppel het ticket aan de gebruiker
        ticket.setUsername(username);
        ticket.setQrCodeImage(new QRCodeImage());
        ticket.setStatus(TicketStatus.VALID);
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