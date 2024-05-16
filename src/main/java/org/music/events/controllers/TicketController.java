package org.music.events.controllers;
import org.music.events.services.QRCodeImageService;
import org.music.events.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
@RequestMapping("tickets")
public class TicketController {
    // TicketService en QRCodeImageService dependencies injecteren
    @Autowired
    private final TicketService ticketService;
    private final QRCodeImageService qrCodeImageService;
    // Constructor voor TicketController met dependency injection van TicketService en QRCodeImageService
    public TicketController(TicketService ticketService, QRCodeImageService qrCodeImageService) {
        this.ticketService = ticketService;
        this.qrCodeImageService = qrCodeImageService;
    }
    // Methode om een ticket te kopen via een HTTP POST-verzoek
    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseTicket(@RequestParam("eventId") Long eventId, @RequestParam("username") String username) {
        ticketService.purchaseTicket(eventId,username);
        return ResponseEntity.ok("Ticket succesvol gekocht!");
    }
    // Methode om een ticket te valideren via een HTTP POST-verzoek
    @GetMapping("/validate")
    public ResponseEntity<String> validateTicket(@RequestParam("qrCode") String qrCode) {
        return ResponseEntity.ok("Ticket succesvol gevalideerd!");
    }


}