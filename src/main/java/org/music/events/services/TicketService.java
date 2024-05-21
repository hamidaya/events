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

@Service
public class TicketService {
    // TicketRepository voor toegang tot ticketgegevens
    //    @Autowired
    //    private QRCodeImageService qrCodeImageService;

    @Autowired
    private TicketRepository ticketRepository;
    // UserRepository voor toegang tot gebruikersgegevens
    private UserRepository userRepository;
    // EventRepository voor toegang tot evenementgegevens
    private EventRepository eventRepository;

    // QRCodeImageRepository voor toegang tot QR-codeafbeeldingsgegevens
    private QRCodeImageRepository qrCodeImageRepository;
    // Constructor voor TicketService met UserRepository, EventRepository en QRCodeImageRepository injectie
    public TicketService(UserRepository userRepository, EventRepository eventRepository, QRCodeImageRepository qrCodeImageRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.qrCodeImageRepository = qrCodeImageRepository;
    }
    // Methode om een ticket te kopen
    public void purchaseTicket(Long eventId, String username) {
        // Nieuw ticket aanmaken
        Ticket ticket = new Ticket();
        // Evenement zoeken op basis van eventId en toevoegen aan het ticket, anders exceptie werpen
        ticket.setEvent(eventRepository.findById(eventId).orElseThrow(() -> new EventListenerRegistrationException("Event not found")));
        // Gebruiker zoeken op basis van username en toevoegen aan het ticket, anders exceptie werpen
        User user = (User) userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Gebruiker niet gevonden"));
        ticket.setUser(user);
        // Save the ticket to the database
        // QR-codeafbeelding zoeken op basis van qrCodeId en toevoegen aan het ticket, anders exceptie werpen
        // ticket.setQrCode(qrCodeImageRepository.findQRCodeImageByQrCodeId(qrCodeId).orElseThrow(() -> new RuntimeException("qr code niet gevonden")));
        //-> new RuntimeException("qrcode niet gevonden"));
        // Ticket opslaan in de database
        ticketRepository.save(ticket);
        // Ticketstatus instellen op GELDIG
        ticket.setStatus(TicketStatus.VALID);
        // Ticket opnieuw opslaan met bijgewerkte status
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
    // Methode om een ticket te valideren
//    public String validateTicket(Long qrCodeId, Long username) {
//        // Ticket zoeken op basis van qrCode
//        Ticket ticket = qrCodeImageRepository.findQRCodeImageByQrCodeId(qrCodeId);
//        // Als ticket bestaat en status GELDIG is
//        if (ticket != null && ticket.getStatus() == TicketStatus.VALID) {
//            // Status van ticket instellen op GEBRUIKT
//            ticket.setStatus(TicketStatus.USED);
//            // Ticket opslaan met bijgewerkte status
//            ticketRepository.save(ticket);
//            // Returnbericht "Ticket is geldig!"
//            return "Ticket is geldig!";
//        } else {
//            // Returnbericht "Ongeldig ticket!"
//            return "Ongeldig ticket!";
        }
//    }


