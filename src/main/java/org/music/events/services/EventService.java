package org.music.events.services;
import org.music.events.dtos.EventRequestDTO;
import org.music.events.dtos.EventRespondsDTO;
import org.music.events.models.Event;
import org.music.events.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
@Service
public class EventService {
    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventRespondsDTO> getAllEvents() {
        List<Event> eventList = eventRepository.findAll();
        return transferEventListToDtoList(eventList);
    }
    public List<EventRespondsDTO> getAllEventsByName(String eventName) {
        List<Event> eventList = eventRepository.findAllEventsByEventNameEqualsIgnoreCase(eventName);
        return transferEventListToDtoList(eventList);
    }
    public List<EventRespondsDTO> transferEventListToDtoList(List<Event> events) {
        List<EventRespondsDTO> eventDtoList = new ArrayList<>();
        for (Event event : events) {
            EventRespondsDTO dto = transferToDto(event);
            if (event.getEventName() != null) {
                dto.setEventName(event.getEventName());
            }
            if (event.getEventLocation() != null) {
                dto.setEventLocation(event.getEventLocation());
            }
            eventDtoList.add(dto);
        }
        return eventDtoList;
    }
//    public EventRespondsDTO getEventById(Long id) {
//
//        if (eventRepository.findById(id).isPresent()){
//            Event event = eventRepository.findById(id).get();
//            EventRespondsDTO dto =transferToDto(event);
//            if(event.getEventName() != null){
//                dto.setEventRespondsDTO(eventService.transferToDto(event.getEventID()));
//            }
//            if(event.getEventController() != null){
//                dto.setEventRespondsDTO(eventService.transferToDto(event.getEventController()));
//            }
//
//            return transferToDto(event);
//        } else {
//            throw new RecordNotFoundException("geen event gevonden");
//        }
//    }

    public EventRespondsDTO addEvent(EventRequestDTO dto) {

        Event event = transferToEvent(dto);
        eventRepository.save(event);

        return transferToDto(event);
    }

    public Event updateEvent(Long eventId, Event updatedEvent) {
        return updatedEvent;
    }
    public void deleteEvent(Long eventId) {
        // Implementation for deleting event
    }
    public Event createEvent(Event event) {
        return event;
    }
    public Event transferToEvent(EventRequestDTO dto) {
        Event event = new Event();
        event.setEventLocation(dto.getEventLocation());
        event.setEventName(dto.getEventName());
        event.setEventPrice(dto.getEventPrice());
        event.setEventStartDate(dto.getEventStartDate());
        event.setAvailableTickets(dto.getAvailableTickets());
        return event;
    }
    public EventRespondsDTO transferToDto(Event event) {
        EventRespondsDTO dto =  new EventRespondsDTO();
        dto.setEventID(event.getEventID());
        dto.setEventName(event.getEventName());
        dto.setEventLocation(event.getEventLocation());
        dto.setEventPrice(event.getEventPrice());
        dto.setEventStartDate(event.getEventStartDate());
        dto.setAvailableTickets(event.getAvailableTickets());
        return dto;
    }

    }
