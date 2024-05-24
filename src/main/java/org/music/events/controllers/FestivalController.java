package org.music.events.controllers;

import jakarta.validation.Valid;
import org.music.events.dtos.FestivalRequestDTO;
import org.music.events.dtos.FestivalRespondsDTO;
import org.music.events.models.Festival;
import org.music.events.services.FestivalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("festivals")

public class FestivalController {
    private final FestivalService festivalService;

    public FestivalController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @GetMapping()
    public ResponseEntity<List<FestivalRespondsDTO>> getAllFestivals(@RequestParam(value = "festivalname", required = false) Optional<String> festivalName) {
        List<FestivalRespondsDTO> festivalRespondsDTOS;
        if (festivalName.isEmpty()) {
            festivalRespondsDTOS = festivalService.getAllFestivals();
        } else {

            festivalRespondsDTOS = List.of();
        }
        return ResponseEntity.ok().body(festivalRespondsDTOS);
    }

    @PostMapping()
    public ResponseEntity<Object> addFestival(@Valid @RequestBody FestivalRequestDTO festivalRequestDTO) {

        FestivalRespondsDTO dto = festivalService.addFestival(festivalRequestDTO);

        return ResponseEntity.created(null).body(dto);

    }

    @PutMapping("/{eventId}")
    public ResponseEntity<FestivalRespondsDTO> updateFestival(@PathVariable Long eventId, @RequestBody FestivalRequestDTO festivalRequestDTO) {

        Festival updatedFestival = festivalService.updateFestival(eventId, festivalRequestDTO);

        return ResponseEntity.ok().body(festivalService.transferToDto(updatedFestival));

    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Object> deleteFestival(@PathVariable Long eventId) {

        festivalService.deleteFestival(eventId);

        return ResponseEntity.noContent().build();

    }

}