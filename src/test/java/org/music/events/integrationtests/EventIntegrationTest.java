package org.music.events.integrationtests;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.music.events.dtos.EventRequestDTO;
import org.music.events.dtos.EventRespondsDTO;
import org.music.events.models.Event;
import org.music.events.repositories.EventRepository;
import org.music.events.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class EventIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EventService eventService;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ObjectMapper objectMapper;
    Event event1;
    Event event2;
    private EventRespondsDTO eventRespondsDTO;
    private EventRequestDTO eventRequestDTO;
    @BeforeEach
    public void setUp() {

        eventRepository.deleteAll();
        event1 = new Event(1L, "party", "mark event", "utrecht",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 33.00, 3411, "dit event alleen voor mark en frans");
        event2 = new Event(2L, "party", "mark event", "utrecht",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 46.00, 1234, "dit event alleen voor mark en frans");
        eventRespondsDTO = new EventRespondsDTO(1L, "party", "mark event", "utrecht",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 456.00, 34, "dit event alleen voor mark en frans");
        eventRequestDTO = new EventRequestDTO("mark party", "party", "amsterdam",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 5556.00, 34, "dit event alleen voor mark en frans");
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    void getAllEvents() throws Exception {
        // Voeg testdata toe
        eventService.addEvent(eventRequestDTO);
        mockMvc.perform(get("/events").with(httpBasic("admin", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].eventName").value("mark party"))
                .andExpect(jsonPath("$[0].eventType").value("party"))
                .andExpect(jsonPath("$[0].eventLocation").value("utrecht"))
                .andExpect(jsonPath("$[0].eventStartDate").value("2024-02-04"))
                .andExpect(jsonPath("$[0].eventEndDate").value("2024-07-15"))
                .andExpect(jsonPath("$[0].availableTickets").value(34))
                .andExpect(jsonPath("$[0].eventPrice").value(5556.00))
                .andExpect(jsonPath("$[0].eventDescription").value("dit event alleen voor mark en frans"));
    }
    @Test
    void getAllEventsByName() throws Exception {
        eventService.addEvent(eventRequestDTO);
        mockMvc.perform(get("/events").param("eventname", "mark party"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].eventName").value("mark party"))
                .andExpect(jsonPath("$[0].eventType").value("party"))
                .andExpect(jsonPath("$[0].eventLocation").value("utrecht"))
                .andExpect(jsonPath("$[0].eventStartDate").value("2024-02-04"))
                .andExpect(jsonPath("$[0].eventEndDate").value("2024-07-15"))
                .andExpect(jsonPath("$[0].availableTickets").value(134))
                .andExpect(jsonPath("$[0].eventPrice").value(5556.00))
                .andExpect(jsonPath("$[0].eventDescription").value("dit event alleen voor mark en frans"));
    }
    @Test
    void addEvent() throws Exception {
        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eventName").value("mark party"))
                .andExpect(jsonPath("$.eventType").value("party"))
                .andExpect(jsonPath("$.eventLocation").value("amsterdam"))
                .andExpect(jsonPath("$.eventStartDate").value("2024-02-04"))
                .andExpect(jsonPath("$.eventEndDate").value("2024-07-15"))
                .andExpect(jsonPath("$.availableTickets").value(34))
                .andExpect(jsonPath("$.eventPrice").value(5556.00))
                .andExpect(jsonPath("$.eventDescription").value("dit event alleen voor mark en frans"));
    }
    @Test
    void updateEvent() throws Exception {
        EventRespondsDTO addedEvent = eventService.addEvent(eventRequestDTO);
        Long eventId = addedEvent.getEventId();
        EventRequestDTO updatedEventRequestDTO = new EventRequestDTO("mark party updated", "party", "amsterdam",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 7777.00, 100, "updated description");
        mockMvc.perform(put("/events/{eventId}", eventId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedEventRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eventName").value("mark party updated"))
                .andExpect(jsonPath("$.eventType").value("party"))
                .andExpect(jsonPath("$.eventLocation").value("amsterdam"))
                .andExpect(jsonPath("$.eventStartDate").value("2024-02-04"))
                .andExpect(jsonPath("$.eventEndDate").value("2024-07-15"))
                .andExpect(jsonPath("$.availableTickets").value(34))
                .andExpect(jsonPath("$.eventPrice").value(77477.00))
                .andExpect(jsonPath("$.eventDescription").value("updated description"));
    }
    @Test
    void deleteEvent() throws Exception {
        EventRespondsDTO addedEvent = eventService.addEvent(eventRequestDTO);
        Long eventId = addedEvent.getEventId();
        mockMvc.perform(delete("/events/{eventId}", eventId))
                .andExpect(status().isNoContent());
    }
}