package org.music.events.controllers;

import org.music.events.services.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/tickets")
public class TicketController {


    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<String> purchaseTicket(@RequestParam("eventId") String eventId, @RequestParam("username") String username) {
        ticketService.purchaseTicket(eventId,username);

        return ResponseEntity.ok("Ticket purchased successfully!");
    }
    @PostMapping("/validate")
    public ResponseEntity<String> validateTicket(@RequestParam("qrCode") String qrCode) {
        return ResponseEntity.ok("Ticket validated successfully!");
    }
}


