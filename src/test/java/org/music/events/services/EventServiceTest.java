package org.music.events.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.music.events.dtos.EventRequestDTO;
import org.music.events.dtos.EventRespondsDTO;
import org.music.events.models.Event;
import org.music.events.repositories.EventRepository;
import org.music.events.services.EventService;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ActiveProfiles("test")
class EventServiceTest {
    @Mock
    EventRepository eventRepository;
    @InjectMocks
    EventService eventService;
    @Captor
    ArgumentCaptor<Event> captor;
    Event event1;
    Event event2;
    Event event3;
    @BeforeEach
    void setUp() {
        event1 = new Event(1L, "mark event", "party", "utrecht", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.80, 456, "dit event alleen voor mark en frans");
        event2 = new Event(2L, "frans event", "festival", "Amsterdam", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.32, 456, "dit event alleen voor mark en frans");
        event3 = new Event(3L, "hamid event", "festival", "nijmegen", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.35, 456, "dit event alleen voor mark en frans");
    }
    @Test
    void getAllEvents() {
        when(eventRepository.findAll()).thenReturn(List.of(event1, event2, event3));
        List<EventRespondsDTO> eventsFound = eventService.getAllEvents();
        assertEquals(event1.getEventName(), eventsFound.get(0).getEventName());
        assertEquals(event2.getEventLocation(), eventsFound.get(1).getEventLocation());
        assertEquals(event3.getEventDescription(), eventsFound.get(2).getEventDescription());
        verify(eventRepository, times(1)).findAll();
    }
}