package org.music.events.services;
import org.hibernate.event.service.spi.EventListenerRegistrationException;
import org.music.events.exceptions.QRCodeException;
import org.music.events.helpers.QRCodeGenerator;
import org.music.events.models.*;
import org.music.events.repositories.EventRepository;
import org.music.events.repositories.QRCodeImageRepository;
import org.music.events.repositories.TicketRepository;
import org.music.events.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    private final QRCodeImageRepository qrCodeImageRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, EventRepository eventRepository, QRCodeImageRepository qrCodeImageRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.qrCodeImageRepository = qrCodeImageRepository;
    }

    @Transactional
    public void purchaseTicket(Long eventId, String username) {
        Ticket ticket = new Ticket();
        ticket.setEvent(eventRepository.findById(eventId).orElseThrow(() -> new EventListenerRegistrationException("Event not found")));
        User user = userRepository.findById(username).orElseThrow(() -> new RuntimeException("Gebruiker niet gevonden"));
        ticket.setUser(user);
        ticketRepository.save(ticket);

        ticket.setStatus(TicketStatus.VALID);

        ticketRepository.save(ticket);

        try {
            byte[] image = QRCodeGenerator.generateQRCodeImage(username, 100, 130);

            QRCodeImage qrcodeImage = new QRCodeImage();
            qrcodeImage.setTicket(ticket);
            qrcodeImage.setImage(image);
            qrCodeImageRepository.save(qrcodeImage);

        } catch (Exception e) {
            throw new QRCodeException();
        }

    }
}




