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
    @GetMapping
    public ResponseEntity<List<FestivalRespondsDTO>> getAllFestivals(@RequestParam(value = "festivalname", required = false) Optional<String> festivalName) {
        List<FestivalRespondsDTO> festivalRespondsDTOS;
        if (festivalName.isEmpty()) {
            festivalRespondsDTOS = festivalService.getAllFestivals();
        } else {
            // Implement method to get festivals by name
            // festivalRespondsDTOS = festivalService.getFestivalsByName(festivalName.get());
            // For now, return empty list
            festivalRespondsDTOS = List.of();
        }
        return ResponseEntity.ok().body(festivalRespondsDTOS);
    }
    // Implement other methods such as getFestival, addFestival, updateFestival, and deleteFestival
    // These methods will interact with FestivalService to perform CRUD operations on festivals
}