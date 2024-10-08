package org.music.events.controllers;

import jakarta.validation.Valid;
import org.music.events.dtos.*;
import org.music.events.models.Party;
import org.music.events.services.FestivalService;
import org.music.events.services.PartyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@ControllerAdvice
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

           partyRespondsDTOS = List.of();
        }
        return ResponseEntity.ok().body(partyRespondsDTOS);
    }
    @PostMapping()

       public ResponseEntity<Object> addParty(@Valid @RequestBody PartyRequestDTO partyRequestDTO) {

        PartyRespondsDTO dto = partyService.addParty(partyRequestDTO);

           return ResponseEntity.created(null).body(dto);

       }

    @PutMapping("/{eventId}")
            public ResponseEntity<PartyRespondsDTO> updateParty(@PathVariable Long eventId, @RequestBody PartyRequestDTO partyRequestDTO) {

        Party updatedParty = partyService.updateParty(eventId, partyRequestDTO);

        return ResponseEntity.ok().body(partyService.transferToDto(updatedParty));

    }


    @DeleteMapping("/{eventId}")
    public ResponseEntity<Object> deleteParty(@PathVariable Long eventId) {

        partyService.deleteParty(eventId);

        return ResponseEntity.noContent().build();

    }

}