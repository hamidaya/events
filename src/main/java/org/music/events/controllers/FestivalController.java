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
//@RequestMapping("festivals")
public class FestivalController {

    private final FestivalService festivalService;

    public FestivalController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @GetMapping("festivals")
    public ResponseEntity<List<FestivalRespondsDTO>> getAllFestivals(@RequestParam(value = "festivalname", required = false) Optional<String> festivalName) {

        List<FestivalRespondsDTO> festivalRespondsDTOS;


        return ResponseEntity.ok().body(festivalName);

    }
}

//@GetMapping("/{festivalId}")
//    public ResponseEntity<Festival> getFestival(@PathVariable ("id")Long festivalId) {
//        Festival festival = festivalService.getFestivalById(festivalId);
//
//        return ResponseEntity.ok().body(festival);
//
//            }
//    @GetMapping("/{festivalname}")
//    public ResponseEntity<Festival> getFestivalName(@PathVariable Long festivalName) {
//
//        Festival festival = festivalService.getFestivalById(festivalName);
//
//        return ResponseEntity.ok().body(festival);
//    }

// @PostMapping("/festivals")
//
//    public ResponseEntity<Object> addFestival(@Valid @RequestBody FestivalRequestDTO festivalRequestDTO) {
//
//     FestivalRespondsDTO dto = festivalService.addFestival(festivalRequestDTO);
//
//        return ResponseEntity.created(null).body(dto);
//
//    }
//
//    @PutMapping("/{festivalId}")
//    public ResponseEntity<Festival> updateFestival(@PathVariable Long festivalId, @RequestBody Festival updatedFestival) {
//        Festival updated = festivalService.updateFestival(festivalId, updatedFestival);
//        return ResponseEntity.ok(updated);
//    }
//
//    @DeleteMapping("/{festivalId}")
//    public ResponseEntity<Void> deleteFestival(@PathVariable Long festivalId) {
//        festivalService.deleteFestival(festivalId);
//        return ResponseEntity.noContent().build();
//    }
