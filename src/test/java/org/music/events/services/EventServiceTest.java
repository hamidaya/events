package org.music.events.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentCaptor;
import org.music.events.dtos.EventRequestDTO;
import org.music.events.dtos.EventRespondsDTO;
import org.music.events.models.Event;
import org.music.events.repositories.EventRepository;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class EventServiceTest {
    @InjectMocks
    private EventService eventService;
    @Mock
    private EventRepository eventRepository;
    @Captor
    private ArgumentCaptor<Event> captor;
    private Event event1;
    private Event event2;
    private Event event3;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        event1 = new Event(1L, "mark event", "party", "utrecht", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.80, 456, "dit event alleen voor mark en frans");
        event2 = new Event(2L, "frans event", "festival", "utrecht", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.32, 456, "dit event alleen voor mark en frans");
        event3 = new Event(3L, "hamid event", "festival", "nijmegen", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.35, 456, "dit event alleen voor mark en frans");
    }
    @Test
    void addEvent() {
        EventRequestDTO eventRequestDTO = new EventRequestDTO("new event", "party", "utrecht", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.80, 456, "description");
        Event eventToSave = new Event(null, "new event", "party", "utrecht", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.80, 456, "description");
        Event savedEvent = new Event(4L, "new event", "party", "utrecht", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.80, 456, "description");
        // Correct gebruik van argument matchers
        when(eventRepository.save(any(Event.class))).thenReturn(savedEvent);
        EventRespondsDTO result = eventService.addEvent(eventRequestDTO);
        assertEquals(savedEvent.getEventName(), result.getEventName());
        assertEquals(savedEvent.getEventLocation(), result.getEventLocation());
        // Gebruik van ArgumentCaptor om de opgeslagen waarde te verifiëren
        verify(eventRepository, times(1)).save(captor.capture());
        Event capturedEvent = captor.getValue();
        assertEquals(eventToSave.getEventName(), capturedEvent.getEventName());
        assertEquals(eventToSave.getEventLocation(), capturedEvent.getEventLocation());

    }
    @Test
    void addEvent_ShouldThrowException_WhenDtoIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            eventService.addEvent(null);
        });
        assertEquals("EventRequestDTO cannot be null", exception.getMessage());
    }

    @Test
    void updateEvent() {
        EventRequestDTO eventRequestDTO = new EventRequestDTO("updated event", "festival", "rotterdam", LocalDate.of(2024, 3, 4), LocalDate.of(2024, 8, 15), 40.00, 500, "updated description");
        Event updatedEvent = new Event(1L, "updated event", "festival", "rotterdam", LocalDate.of(2024, 3, 4), LocalDate.of(2024, 8, 15), 40.00, 500, "updated description");
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event1));
        when(eventRepository.save(any(Event.class))).thenReturn(updatedEvent);
        Event result = eventService.updateEvent(1L, eventRequestDTO);
        assertEquals(updatedEvent.getEventName(), result.getEventName());
        assertEquals(updatedEvent.getEventLocation(), result.getEventLocation());

        verify(eventRepository, times(1)).findById(1L);
        verify(eventRepository, times(1)).save(captor.capture());
        Event capturedEvent = captor.getValue();
        assertEquals(updatedEvent.getEventName(), capturedEvent.getEventName());
        assertEquals(updatedEvent.getEventLocation(), capturedEvent.getEventLocation());
    }
    @Test
    void deleteEvent() {
        eventService.deleteEvent(1L);
        verify(eventRepository, times(1)).deleteById(1L);
    }

    @Test
    void testAddEvent() {
            if (eventRepository.existsById(1L)) {
                throw new AssertionError("Event already exists");
            }
        }
    }
