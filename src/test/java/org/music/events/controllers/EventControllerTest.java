//package org.music.events.controllers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.music.events.dtos.EventRequestDTO;
//import org.music.events.dtos.EventRespondsDTO;
//import org.music.events.filter.JwtRequestFilter;
//import org.music.events.models.Event;
//import org.music.events.repositories.EventRepository;
//import org.music.events.services.EventService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//
//import org.springframework.test.web.servlet.MockMvc;
//import java.time.LocalDate;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@WebMvcTest(EventController.class)
//@ActiveProfiles("test")
//public class EventControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private EventService eventService;
//    @MockBean
//    private EventRepository eventRepository;
//    @MockBean
//    private JwtRequestFilter jwtRequestFilter;
//
//    Event event1;
//    Event event2;
//    EventRespondsDTO eventRespondsDTO;
//    EventRequestDTO eventRequestDTO;
//
//    @BeforeEach
//    public void setUp() {
//        event1 = new Event(1L, "party", "mark event", "utrecht",
//                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 33.00, 3411, "dit event alleen voor mark en frans");
//
//        event2 = new Event(2L, "party", "mark event", "utrecht",
//                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 46.00, 1234, "dit event alleen voor mark en frans");
//
//        eventRespondsDTO = new EventRespondsDTO(1L, "party", "mark event", "utrecht",
//                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 26.00, 2334, "dit event alleen voor mark en frans");
//
//        eventRequestDTO = new EventRequestDTO("mark event", "party", "utrecht",
//                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 35.00, 3324, "dit event alleen voor mark en frans");
//    }
//
//    @Test
////    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
//    void getAllEvents() throws Exception {
//        eventService.addEvent(eventRequestDTO);
//        mockMvc.perform(get("/events").with(httpBasic("admin", "password"))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].eventName").value("mark event"))
//                .andExpect(jsonPath("$[0].eventType").value("party"))
//                .andExpect(jsonPath("$[0].eventLocation").value("utrecht")) // Corrected location
//                .andExpect(jsonPath("$[0].eventStartDate").value("2024-02-04"))
//                .andExpect(jsonPath("$[0].eventEndDate").value("2024-07-15"))
//                .andExpect(jsonPath("$[0].availableTickets").value(34)) // Corrected available tickets
//                .andExpect(jsonPath("$[0].eventPrice").value(35.00)) // Corrected event price
//                .andExpect(jsonPath("$[0].eventDescription").value("dit event alleen voor mark en frans"));
//    }
//
//}
