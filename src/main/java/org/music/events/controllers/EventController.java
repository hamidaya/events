package org.music.events.controllers;

import org.music.events.models.Event;
import org.music.events.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("events")
public class EventController {



    @Autowired
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable Long eventId) {
        Event event = eventService.getEventById(eventId);
        return ResponseEntity.ok(event);
    }
    @GetMapping("/{eventname}")
    public ResponseEntity<Event> getEventName(@PathVariable Long eventName) {
        Event event = eventService.getEventById(eventName);
        return ResponseEntity.ok(event);
    }

    @PostMapping("/events")

    public ResponseEntity<?> addEvent(@Valid @RequestBody eventDto eventDto) {
        eventDto dto;

// Hier gebruiken we weer een service methode in plaats van direct de repository aan te spreken.

        if(eventDto.getFestival() != null && eventDto.getcampingAvailable() != null) {
            dto = festivalService.addEvent(mapper.toFestival(eventDto));
        } else if(festivalInputDto.getHdr()){
            dto = partyService.addParty(mapper.toParty(eventDto));
        } else {
            return ResponseEntity.badRequest().body("Niet duidelijk of het een party of festival is");
        }

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