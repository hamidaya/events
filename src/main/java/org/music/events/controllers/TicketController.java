package org.music.events.controllers;
import com.google.zxing.WriterException;
import org.music.events.helpers.QRCodeGenerator;
import org.music.events.models.QRCodeImage;
import org.music.events.services.QRCodeImageService;
import org.music.events.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Base64;

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
        ticketService.purchaseTicket(eventId, username);
        return ResponseEntity.ok("Ticket succesvol gekocht!");
    }


    // Methode om een ticket te valideren via een HTTP POST-verzoek
    @GetMapping("/validate")
    public ResponseEntity<Object> validateTicket(@RequestParam("qrCodeId") Long qrCodeId, @RequestParam("username") String username) {
        ticketService.purchaseTicket(qrCodeId, username);
        return ResponseEntity.ok("qrCodeId succesvol gevalideerd met username!");

    }

//    @GetMapping("/{id}/validate")
//
//    public ResponseEntity<byte[]> validateTicket(@PathVariable("qrCodeId") String username){
//
//        QRCodeImage qrCodeImage = ticketService.validateTicket(qrCode, username);
//
//        MediaType mediaType;
//
//        try {
//            mediaType = MediaType.parseMediaType(qrCodeImage.getContenType());
//        } catch (InvalidMediaTypeException ignore){
//            mediaType = MediaType.APPLICATION_OCTET_STREAM;
//        }
//
//
//
//
//        return ResponseEntity
//                .ok()
//                .contentType(mediaType)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + qrCodeImage.getImage())
//                .body(qrCodeImage.getImage());
//    }

}



