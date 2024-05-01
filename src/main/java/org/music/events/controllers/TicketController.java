package org.music.events.controllers;

import org.music.events.dtos.TicketRespondsDTO;
import org.music.events.services.TicketService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tickets")

public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
     public ResponseEntity<List<TicketRespondsDTO>> getAllTickets(@RequestParam("eventName") String eventName)
                                                                   {
         List<TicketRespondsDTO> dtos;

         if (eventName.isEmpty()) {

             dtos = ticketService.getAllTickets();
         } else {

             dtos = ticketService.getAllTickets();
         }
         return ResponseEntity.ok().body(dtos);
     }
    }


