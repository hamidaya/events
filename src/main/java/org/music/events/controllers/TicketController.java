package org.music.events.controllers;

import org.music.events.exceptions.TicketNotFoundException;
import org.music.events.services.QRCodeImageService;
import org.music.events.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("tickets")
public class TicketController {
    @Autowired
    private final TicketService ticketService;
    private final QRCodeImageService qrCodeImageService;

    public TicketController(TicketService ticketService, QRCodeImageService qrCodeImageService) {
        this.ticketService = ticketService;
        this.qrCodeImageService = qrCodeImageService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseTicket(@RequestParam("eventId") Long eventId, @RequestParam("username") String username) {
        ticketService.purchaseTicket(eventId, username);
        return ResponseEntity.ok("Ticket succesvol gekocht!");
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateTicket(@RequestParam("ticketId") Long ticketId) {
        try {
            String validationMessage = ticketService.validateTicket(ticketId);
            return ResponseEntity.ok(validationMessage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validatie mislukt: " + e.getMessage());
        }
    }
    }





