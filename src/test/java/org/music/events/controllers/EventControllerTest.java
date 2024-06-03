package org.music.events.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.music.events.filter.JwtRequestFilter;
import org.music.events.services.CustomUserDetailsService;
import org.music.events.services.EventService;
import org.music.events.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.music.events.dtos.EventRequestDTO;
import org.music.events.dtos.EventRespondsDTO;
import org.music.events.models.Event;
import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest

class EventControllerTest {

    @MockBean
    private EventService eventService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUtil jwtUtilTest;


    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @InjectMocks
    JwtRequestFilter jwtRequestFilter;


    Event event1;
    Event event2;
    Event event3;

    EventRespondsDTO eventRespondsDTO2 = new EventRespondsDTO();

    @BeforeEach
    public void setUp() {

        eventRespondsDTO2 = new EventRespondsDTO(1L,"party", "mark event", "utrecht",  LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15),456.00,34,"dit event alleen voor mark en frans");

        event1 = new Event(1L, "mark event", "party", "utrecht", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.80, 456, "dit event alleen voor mark en frans");
        event2 = new Event(2L, "frans event", "festival", "Amsterdam", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.32, 456, "dit event alleen voor mark en frans");
        event3 = new Event(3L, "hamid event", "festival", "nijmegen", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.35, 456, "dit event alleen voor mark en frans");
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")

    void getAllEvents() throws Exception {
//        List<EventRespondsDTO> = List.of(eventRespondsDTO2);
//        when(eventService.getAllEvents()).thenReturn(eventRespondsDTOArrayList);

        mockMvc.perform(get("/events").with(httpBasic("admin", "password")))
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



