package org.music.events.controllers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.music.events.dtos.EventRequestDTO;
import org.music.events.dtos.EventRespondsDTO;
import org.music.events.models.Event;
import org.music.events.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(EventController.class)
@ActiveProfiles("test")

public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    Event event1;
    Event event2;
    EventRespondsDTO eventRespondsDTO;
    EventRequestDTO eventRequestDTO;

    @BeforeEach
    public void setUp() {

        event1 = new Event(1L, "party", "mark event", "utrecht",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 33.00, 3411, "dit event alleen voor mark en frans");

        event2 = new Event(2L, "party", "mark event", "utrecht",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 46.00, 1234, "dit event alleen voor mark en frans");

        eventRespondsDTO = new EventRespondsDTO(1L, "party", "mark event", "utrecht",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 26.00, 2334, "dit event alleen voor mark en frans");

        eventRequestDTO = new EventRequestDTO("mark event", "party", "utrecht",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 35.00, 3324, "dit event alleen voor mark en frans");
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    void getAllEvents() throws Exception {
        when(eventService.getAllEvents()).thenReturn(List.of(eventRespondsDTO));
        mockMvc.perform(get("/events")
                        .with(httpBasic("admin", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
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
}
    //    void getAllEventsByName() throws Exception {
//        when(eventService.getAllEventsByName("mark event")).thenReturn(List.of(eventRespondsDTO));
//        mockMvc.perform(get("/events").param("eventname", "mark event"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].eventId").value(1L))
//                .andExpect(jsonPath("$[0].eventName").value("mark event"))
//                .andExpect(jsonPath("$[0].eventType").value("party"))
//                .andExpect(jsonPath("$[0].eventLocation").value("utrecht"))
//                .andExpect(jsonPath("$[0].eventStartDate").value("2024-02-04"))
//                .andExpect(jsonPath("$[0].eventEndDate").value("2024-07-15"))
//                .andExpect(jsonPath("$[0].availableTickets").value(34))
//                .andExpect(jsonPath("$[0].eventPrice").value(456.00))
//                .andExpect(jsonPath("$[0].eventDescription").value("dit event alleen voor mark en frans"));
//    }
//    @Test
//    void addEvent() throws Exception {
//        when(eventService.addEvent(any(EventRequestDTO.class))).thenReturn(eventRespondsDTO);
//        mockMvc.perform(post("/events")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(eventRequestDTO)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.eventId").value(1L))
//                .andExpect(jsonPath("$.eventName").value("mark event"))
//                .andExpect(jsonPath("$.eventType").value("party"))
//                .andExpect(jsonPath("$.eventLocation").value("utrecht"))
//                .andExpect(jsonPath("$.eventStartDate").value("2024-02-04"))
//                .andExpect(jsonPath("$.eventEndDate").value("2024-07-15"))
//                .andExpect(jsonPath("$.availableTickets").value(34))
//                .andExpect(jsonPath("$.eventPrice").value(456.00))
//                .andExpect(jsonPath("$.eventDescription").value("dit event alleen voor mark en frans"));
//    }
//    @Test
//    void updateEvent() throws Exception {
//        Event updatedEvent = new Event(1L, "mark event updated", "party", "utrecht",
//                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.80, 456, "dit event alleen voor mark en frans");
//        EventRespondsDTO updatedEventRespondsDTO = new EventRespondsDTO(1L, "party", "mark event updated", "utrecht",
//                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 456.00, 34, "dit event alleen voor mark en frans");
//        when(eventService.updateEvent(anyLong(), any(EventRequestDTO.class))).thenReturn(updatedEvent);
//        when(eventService.transferToDto(updatedEvent)).thenReturn(updatedEventRespondsDTO);
//        mockMvc.perform(put("/events/{eventId}", 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(eventRequestDTO)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.eventId").value(1L))
//                .andExpect(jsonPath("$.eventName").value("mark event updated"))
//                .andExpect(jsonPath("$.eventType").value("party"))
//                .andExpect(jsonPath("$.eventLocation").value("utrecht"))
//                .andExpect(jsonPath("$.eventStartDate").value("2024-02-04"))
//                .andExpect(jsonPath("$.eventEndDate").value("2024-07-15"))
//                .andExpect(jsonPath("$.availableTickets").value(34))
//                .andExpect(jsonPath("$.eventPrice").value(456.00))
//                .andExpect(jsonPath("$.eventDescription").value("dit event alleen voor mark en frans"));
//    }
//    @Test
//    void deleteEvent() throws Exception {
//        mockMvc.perform(delete("/events/{eventId}", 1L))
//                .andExpect(status().isNoContent());
//    }
//}