package org.music.events.controllers;

import org.music.events.dtos.FestivalRespondsDTO;
import org.music.events.dtos.PartyRespondsDTO;
import org.music.events.services.FestivalService;
import org.music.events.services.PartyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("partys")
public class PartyController {
    private final PartyService partyService;
    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }
    @GetMapping
    public ResponseEntity<List<PartyRespondsDTO>> getAllPartys(@RequestParam(value = "partyname", required = false) Optional<String> partyName) {
        List<PartyRespondsDTO> partyRespondsDTOS;
        if (partyName.isEmpty()) {
            partyRespondsDTOS = partyService.getAllPartys();
        } else {
            // Implement method to get partys by name
            // partyRespondsDTOS = partyService.getFestivalsByName(partyName.get());
            // For now, return empty list
            partyRespondsDTOS = List.of();
        }
        return ResponseEntity.ok().body(partyRespondsDTOS);
    }
    // Implement other methods such as getFestival, addFestival, updateFestival, and deleteFestival
    // These methods will interact with FestivalService to perform CRUD operations on partys
}