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
//@RequestMapping("events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("events")
    public ResponseEntity<List<EventRespondsDTO>> getAllEvents(@RequestParam(value = "eventname", required = false) Optional<String> eventName) {

        List<EventRespondsDTO> dtos;

        if (eventName.isEmpty()){

            dtos = eventService.getAllEvents();


        } else {

            dtos = eventService.getAllEventsByName(eventName.get());

        }

        return ResponseEntity.ok().body(dtos);

    }

//@GetMapping("/{eventId}")
//    public ResponseEntity<Event> getEvent(@PathVariable ("id")Long eventId) {
//        Event event = eventService.getEventById(eventId);
//
//        return ResponseEntity.ok().body(event);
//
//            }
//    @GetMapping("/{eventname}")
//    public ResponseEntity<Event> getEventName(@PathVariable Long eventName) {
//
//        Event event = eventService.getEventById(eventName);
//
//        return ResponseEntity.ok().body(event);
//    }

 @PostMapping("/events")

    public ResponseEntity<Object> addEvent(@Valid @RequestBody EventRequestDTO eventRequestDTO) {

     EventRespondsDTO dto = eventService.addEvent(eventRequestDTO);


        return ResponseEntity.created(null).body(dto);

    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long eventId, @RequestBody Event updatedEvent) {
        Event updated = eventService.updateEvent(eventId, updatedEvent);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }
}