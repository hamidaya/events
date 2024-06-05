package org.music.events.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.music.events.dtos.EventRequestDTO;
import org.music.events.dtos.EventRespondsDTO;
import org.music.events.models.Event;
import org.music.events.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@WebMvcTest(EventController.class)

@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EventService eventService;
    @InjectMocks
    private EventController eventController;
    @Autowired
    private ObjectMapper objectMapper;
    private EventRespondsDTO eventRespondsDTO;
    private EventRequestDTO eventRequestDTO;
    @BeforeEach
    public void setUp() {
        eventRespondsDTO = new EventRespondsDTO(1L, "party", "mark event", "utrecht",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 456.00, 34, "dit event alleen voor mark en frans");
        eventRequestDTO = new EventRequestDTO("party", "mark event", "utrecht",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 456.00, 34, "dit event alleen voor mark en frans");
    }
    @Test
//    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    void getAllEvents() throws Exception {
        when(eventService.getAllEvents()).thenReturn(List.of(eventRespondsDTO));
        mockMvc.perform(get("/events")
                        .with(httpBasic("admin", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].eventId").value(1L))
                .andExpect(jsonPath("$[0].eventName").value("mark event"))
                .andExpect(jsonPath("$[0].eventType").value("party"))
                .andExpect(jsonPath("$[0].eventLocation").value("utrecht"))
                .andExpect(jsonPath("$[0].eventStartDate").value("2024-02-04"))
                .andExpect(jsonPath("$[0].eventEndDate").value("2024-07-15"))
                .andExpect(jsonPath("$[0].availableTickets").value(34))
                .andExpect(jsonPath("$[0].eventPrice").value(456.00))
                .andExpect(jsonPath("$[0].eventDescription").value("dit event alleen voor mark en frans"));
    }
    @Test
    void getAllEventsByName() throws Exception {
        when(eventService.getAllEventsByName("mark event")).thenReturn(List.of(eventRespondsDTO));
        mockMvc.perform(get("/events").param("eventname", "mark event"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].eventId").value(1L))
                .andExpect(jsonPath("$[0].eventName").value("mark event"))
                .andExpect(jsonPath("$[0].eventType").value("party"))
                .andExpect(jsonPath("$[0].eventLocation").value("utrecht"))
                .andExpect(jsonPath("$[0].eventStartDate").value("2024-02-04"))
                .andExpect(jsonPath("$[0].eventEndDate").value("2024-07-15"))
                .andExpect(jsonPath("$[0].availableTickets").value(34))
                .andExpect(jsonPath("$[0].eventPrice").value(456.00))
                .andExpect(jsonPath("$[0].eventDescription").value("dit event alleen voor mark en frans"));
    }
    @Test
    void addEvent() throws Exception {
        when(eventService.addEvent(any(EventRequestDTO.class))).thenReturn(eventRespondsDTO);
        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eventId").value(1L))
                .andExpect(jsonPath("$.eventName").value("mark event"))
                .andExpect(jsonPath("$.eventType").value("party"))
                .andExpect(jsonPath("$.eventLocation").value("utrecht"))
                .andExpect(jsonPath("$.eventStartDate").value("2024-02-04"))
                .andExpect(jsonPath("$.eventEndDate").value("2024-07-15"))
                .andExpect(jsonPath("$.availableTickets").value(34))
                .andExpect(jsonPath("$.eventPrice").value(456.00))
                .andExpect(jsonPath("$.eventDescription").value("dit event alleen voor mark en frans"));
    }
    @Test
    void updateEvent() throws Exception {
        Event updatedEvent = new Event(1L, "mark event updated", "party", "utrecht",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.80, 456, "dit event alleen voor mark en frans");
        EventRespondsDTO updatedEventRespondsDTO = new EventRespondsDTO(1L, "party", "mark event updated", "utrecht",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 456.00, 34, "dit event alleen voor mark en frans");
        when(eventService.updateEvent(anyLong(), any(EventRequestDTO.class))).thenReturn(updatedEvent);
        when(eventService.transferToDto(updatedEvent)).thenReturn(updatedEventRespondsDTO);
        mockMvc.perform(put("/events/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eventId").value(1L))
                .andExpect(jsonPath("$.eventName").value("mark event updated"))
                .andExpect(jsonPath("$.eventType").value("party"))
                .andExpect(jsonPath("$.eventLocation").value("utrecht"))
                .andExpect(jsonPath("$.eventStartDate").value("2024-02-04"))
                .andExpect(jsonPath("$.eventEndDate").value("2024-07-15"))
                .andExpect(jsonPath("$.availableTickets").value(34))
                .andExpect(jsonPath("$.eventPrice").value(456.00))
                .andExpect(jsonPath("$.eventDescription").value("dit event alleen voor mark en frans"));
    }
    @Test
    void deleteEvent() throws Exception {
        doNothing().when(eventService).deleteEvent(anyLong());
        mockMvc.perform(delete("/events/1"))
                .andExpect(status().isNoContent());
    }
}