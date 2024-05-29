package org.music.events.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.music.events.dtos.EventRequestDTO;
import org.music.events.dtos.EventRespondsDTO;
import org.music.events.models.Event;
import org.music.events.services.EventService;


import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    Event event1;
    Event event2;
    Event event3;

    EventRequestDTO eventRequestDTO1;
    EventRequestDTO eventRequestDTO2;

    EventRespondsDTO eventRespondsDTO1;
    EventRespondsDTO eventRespondsDTO2;

    @BeforeEach
    public void setUp() {
        event1 = new Event(1L, "mark event", "party", "utrecht", LocalDate.of(2024, 02, 04), LocalDate.of(2024, 07, 15), 34, 456, "dit event alleen voor mark en frans");
        event2 = new Event(2L, "frans event", "festival", "Amsterdam", LocalDate.of(2024, 02, 04), LocalDate.of(2024, 07, 15), 34, 456, "dit event alleen voor mark en frans");
        event3 = new Event(3L, "hamid event", "festival", "nijmegen", LocalDate.of(2024, 02, 04), LocalDate.of(2024, 07, 15), 34, 456, "dit event alleen voor mark en frans");

    }

    @Test
    void getAllEvents() throws Exception {
        given(eventService.getAllEvents()).willReturn(List.of(eventRespondsDTO1,eventRespondsDTO2));

        mockMvc.perform(get("/events"))
                .andExpect(status().isOk())


                .andExpect(jsonPath("$[0].eventId").value("9789076174105"))
                .andExpect(jsonPath("$[0].eventName").value("Harry Potter"))
                .andExpect(jsonPath("$[0].subtitle").value("en de steen der wijzen"))
                .andExpect(jsonPath("$[0].genre").value("fiction"))
                .andExpect(jsonPath("$[0].language").value("NL"))
                .andExpect(jsonPath("$[0].type").value("paperback"))
                .andExpect(jsonPath("$[0].publisher").value("uitgeverij de Harmonie"))
                .andExpect(jsonPath("$[0].authorDto.id").value("1"))
                .andExpect(jsonPath("$[0].authorDto.initials").value("J.K."));

        String isbn, String title, String subtitle, String genre, String language, String type,
                String publisher, AuthorDto authorDto)


        }
    }
