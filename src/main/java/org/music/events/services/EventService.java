package org.music.events.services;

import org.music.events.models.Event;
import org.music.events.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();

    }

    public Event getEventById(Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        return optionalEvent.orElse(null);

    }

    public Event updateEvent (Long eventId, Event updatedEvent){
        return updatedEvent;
    }

    public void deleteEvent (Long eventId){
    }

    public Event createEvent(Event event) {
        return event;
    }
}

