package org.music.events.services;
import jakarta.persistence.EntityNotFoundException;
import org.music.events.dtos.EventRequestDTO;
import org.music.events.dtos.EventRespondsDTO;
import org.music.events.exceptions.EventNotFoundException;
import org.music.events.models.Event;
import org.music.events.repositories.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class EventService {
    private final EventRepository eventRepository;
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @Transactional(readOnly = true)
    public List<EventRespondsDTO> getAllEvents() {
        List<Event> eventList = eventRepository.findAll();
        return transferEventListToDtoList(eventList);
    }
    @Transactional(readOnly = true)
    public List<EventRespondsDTO> getAllEventsByName(String eventName) {
        List<Event> eventList = eventRepository.findAllEventsByEventNameEqualsIgnoreCase(eventName);
        return transferEventListToDtoList(eventList);
    }
    private List<EventRespondsDTO> transferEventListToDtoList(List<Event> events) {
        return events.stream()
                .map(this::transferToDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public EventRespondsDTO addEvent(EventRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("EventRequestDTO cannot be null");
        }
        Event event = transferToEvent(dto);
        eventRepository.save(event);
        return transferToDto(event);
    }
    @Transactional
    public Event updateEvent(Long eventId, EventRequestDTO eventRequestDTO) {
        Event eventToUpdate = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event id " + eventId + " not found"));
        updateEventDetails(eventToUpdate, eventRequestDTO);
        return eventRepository.save(eventToUpdate);
    }
    @Transactional
    public void deleteEvent(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new EventNotFoundException("Event id " + eventId + " not found");
        }
        eventRepository.deleteById(eventId);
    }
    private void updateEventDetails(Event eventToUpdate, EventRequestDTO eventRequestDTO) {
        eventToUpdate.setEventName(eventRequestDTO.getEventName());
        eventToUpdate.setEventLocation(eventRequestDTO.getEventLocation());
        eventToUpdate.setEventType(eventRequestDTO.getEventType());
        eventToUpdate.setEventStartDate(eventRequestDTO.getEventStartDate());
        eventToUpdate.setEventEndDate(eventRequestDTO.getEventEndDate());
        eventToUpdate.setEventPrice(eventRequestDTO.getEventPrice());
        eventToUpdate.setEventDescription(eventRequestDTO.getEventDescription());
        eventToUpdate.setAvailableTickets(eventRequestDTO.getAvailableTickets());
    }
    private Event transferToEvent(EventRequestDTO dto) {
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
        EventRespondsDTO dto = new EventRespondsDTO();
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