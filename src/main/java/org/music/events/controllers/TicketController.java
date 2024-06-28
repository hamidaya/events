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
    public ResponseEntity<Object> validateTicket(@RequestParam("qrCodeId") Long qrCodeId, @RequestParam("username") String username) {
        ticketService.purchaseTicket(qrCodeId, username);
        return ResponseEntity.ok("qrCodeId succesvol gevalideerd met username!");

    }

}



