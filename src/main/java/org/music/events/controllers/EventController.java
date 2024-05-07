package org.music.events.controllers;

import jakarta.validation.Valid;
import org.music.events.dtos.EventRespondsDTO;
import org.music.events.dtos.EventRequestDTO;
import org.music.events.models.Event;
import org.music.events.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping()
    public ResponseEntity<List<EventRespondsDTO>> getAllEvents(@RequestParam(value = "eventname", required = false) Optional<String> eventName) {

        List<EventRespondsDTO> dtos;

        if (eventName.isEmpty()){

            dtos = eventService.getAllEvents();


        } else {

            dtos = eventService.getAllEventsByName(eventName.get());

        }

        return ResponseEntity.ok().body(dtos);

    }

 @PostMapping()

    public ResponseEntity<Object> addEvent(@Valid @RequestBody EventRequestDTO eventRequestDTO) {

     EventRespondsDTO dto = eventService.addEvent(eventRequestDTO);

        return ResponseEntity.created(null).body(dto);

    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventRespondsDTO> updateEvent(@PathVariable Long eventId, @RequestBody EventRequestDTO eventRequestDTO) {

        Event updatedEvent = eventService.updateEvent(eventId, eventRequestDTO);

        return ResponseEntity.ok().body(eventService.transferToDto(updatedEvent));

            }
    @DeleteMapping("/{eventId}")
    public ResponseEntity<Object> deleteEvent(@PathVariable Long eventId) {

        eventService.deleteEvent(eventId);

        return ResponseEntity.noContent().build();

    }

}