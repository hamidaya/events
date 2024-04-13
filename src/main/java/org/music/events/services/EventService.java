package org.music.events.services;

import org.music.events.dtos.EventRequestDTO;
import org.music.events.dtos.EventRespondsDTO;
import org.music.events.dtos.TelevisionDto;
import org.music.events.models.Event;
import org.music.events.models.Television;
import org.music.events.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;


@Service
public class EventService {

    private final EventRepository eventRepository;


    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public List<EventRespondsDTO> getAllEvents() {
        List<Event> eventList = eventRepository.findAll();
        return transferTvListToDtoList(eventList);
    }

    public List<EventRespondsDTO> findAllEventsByNameEqualsIgnoreCase(String eventName) {
        List<Event>eventList = eventRepository.findAllEventsByEventNameEqualsIgnoreCase(eventName);
        return transferEventListToDtoList(eventList);
    }


//    public List<EventRespondsDTO> transferEventListToDtoList(List<Event> Events){
//        List<EventRespondsDTO> eventDtoList = new ArrayList<>();
//
//        for(Event event : events) {
//            EventRespondsDTO dto = transferToDto(event);
//            if(event.getEventName() != null){
//                dto.seteventNameDto(ciModuleService.transferToDto(tv.getCiModule()));
//            }
//            if(event.getRemoteController() != null){
//                dto.setRemoteControllerDto(remoteControllerService.transferToDto(tv.getRemoteController()));
//            }
//            eventDtoList.add(dto);
//        }
//        return eventDtoList;
//    }


    private List<EventRequestDTO> transferEventListToDtoList(List<Event> eventList) {
        return transferEventListToDtoList(eventList);
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

