package org.music.events.services;
import jakarta.persistence.EntityNotFoundException;
import org.music.events.dtos.EventRequestDTO;
import org.music.events.dtos.EventRespondsDTO;
import org.music.events.models.Event;
import org.music.events.repositories.EventRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

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

    public EventRespondsDTO addEvent(EventRequestDTO dto) {

        Event event = transferToEvent(dto);
        eventRepository.save(event);

        return transferToDto(event);
    }

    public Event updateEvent(Long eventId, EventRequestDTO eventRequestDTO) {
        Event eventToUpdate = eventRepository.findById(eventId).orElseThrow();
        new EntityNotFoundException("Event with id " + eventId + " not found");

        eventToUpdate.setEventName(eventRequestDTO.getEventName());
        eventToUpdate.setEventLocation(eventRequestDTO.getEventLocation());
        eventToUpdate.setEventType(eventRequestDTO.getEventType());
        eventToUpdate.setEventStartDate(eventRequestDTO.getEventStartDate());
        eventToUpdate.setEventEndDate(eventRequestDTO.getEventEndDate());
        eventToUpdate.setEventLocation(eventRequestDTO.getEventLocation());
        eventToUpdate.setEventPrice(eventRequestDTO.getEventPrice());
        eventToUpdate.setEventDescription(eventRequestDTO.getEventDescription());

        return eventRepository.save(eventToUpdate);

    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
        new EntityNotFoundException("Event with id " + eventId + "deleted successfully");


    }


    public Event transferToEvent(EventRequestDTO dto) {
        Event event = new Event();
        event.setEventLocation(dto.getEventLocation());
        event.setEventName(dto.getEventName());
        event.setEventPrice(dto.getEventPrice());
        event.setEventStartDate(dto.getEventStartDate());
        event.setEventEndDate(dto.getEventEndDate());
        event.setAvailableTickets(dto.getAvailableTickets());
        event.setEventType(dto.getEventType());
        event.setEventDescription(dto.getEventDescription());

        return event;
    }
    public EventRespondsDTO transferToDto(Event event) {
        EventRespondsDTO dto =  new EventRespondsDTO();
        dto.setEventId(event.getEventId());
        dto.setEventName(event.getEventName());
        dto.setEventLocation(event.getEventLocation());
        dto.setEventPrice(event.getEventPrice());
        dto.setEventStartDate(event.getEventStartDate());
        dto.setEventEndDate(event.getEventEndDate());
        dto.setAvailableTickets(event.getAvailableTickets());
        dto.setEventType(event.getEventType());
        dto.setEventDescription(event.getEventDescription());

        return dto;
    }

    }
