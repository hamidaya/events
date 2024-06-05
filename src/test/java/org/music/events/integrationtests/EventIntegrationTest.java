package org.music.events.integrationtests;

import org.music.events.dtos.EventRespondsDTO;
import org.music.events.models.Event;
import org.music.events.models.Festival; // vragen Mark of andere model nodig is
import org.music.events.models.Party; // vragen Mark of andere model nodig is.
import org.music.events.repositories.EventRepository;
import org.music.events.services.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class EventIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;


    Event event1;
    Event event2;
    Event event3;

    EventRespondsDTO eventRespondsDto1;
    EventRespondsDTO eventRespondsDto2;
    EventRespondsDTO eventRespondsDto3;



    @BeforeEach
    public void setUp() {
        if(eventRepository.count()>0) {
            eventRepository.deleteAll();
        }
//        UUID uuid1 = 1L;
//        UUID uuid2 = 2L;

        event1 = new Event(1L, "mark event", "party", "utrecht", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.80, 456, "dit event alleen voor mark en frans");
        event2 = new Event(2L, "frans event", "festival", "Amsterdam", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.32, 456, "dit event alleen voor mark en frans");
        event3 = new Event(3L, "hamid event", "festival", "nijmegen", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.35, 456, "dit event alleen voor mark en frans");

        eventRespondsDto1 = new EventRespondsDTO(1L, "mark event", "party", "utrecht", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.80, 456, "dit event alleen voor mark en frans");
        eventRespondsDto2 = new EventRespondsDTO(2L, "mark event", "party", "utrecht", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.80, 456, "dit event alleen voor mark en frans");
        eventRespondsDto3 = new EventRespondsDTO(3L, "mark event", "party", "utrecht", LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.80, 456, "dit event alleen voor mark en frans");

        eventRepository.save(event1);
        eventRepository.save(event2);
        eventRepository.save(event3);
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    void getAllEvents() throws Exception {

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

//    @Test
//    void getAuthor() throws Exception {
////            given(authorService.getAuthor(author1.getId().toString())).willReturn(authorDto1);
//
//        mockMvc.perform(get("/authors/" + author1.getId().toString()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("id").value(author1.getId().toString()))
//                .andExpect(jsonPath("initials").value("J.K."))
//                .andExpect(jsonPath("firstname").value("Joanne Kathleen"))
//                .andExpect(jsonPath("lastname").value("Rowling"))
//                .andExpect(jsonPath("dateOfBirth").value("1965-07-31"))
//                .andExpect(jsonPath("gender").value("FEMALE"));
//    }
//
//    @Test
//    void saveAuthor() throws Exception {
////            given(authorService.saveAuthor(authorDto1)).willReturn(authorDto1);
//
//        mockMvc.perform(post("/authors")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(authorDto3)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("id").value(3))
//                .andExpect(jsonPath("initials").value("R."))
//                .andExpect(jsonPath("firstname").value("Roald"))
//                .andExpect(jsonPath("lastname").value("Dahl"))
//                .andExpect(jsonPath("dateOfBirth").value("1916-09-13"))
//                .andExpect(jsonPath("gender").value("MALE"));
//    }
//
//    @Test
//    void updateAuthor() throws Exception {
////            given(authorService.updateAuthor(authorDto2, any(UUID.class))).willReturn(authorDto3);
//
//        mockMvc.perform(put("/authors/" + author1.getId().toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(authorDto2)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("id").value(author1.getId().toString()))
//                .andExpect(jsonPath("initials").value("R."))
//                .andExpect(jsonPath("firstname").value("Roald"))
//                .andExpect(jsonPath("lastname").value("Dahl"))
//                .andExpect(jsonPath("dateOfBirth").value("1916-09-13"))
//                .andExpect(jsonPath("gender").value("MALE"));
//    }
//
//    @Test
//    void deleteAuthor() throws Exception {
//        mockMvc.perform(delete("/authors/" + author1.getId().toString()))
//                .andExpect(status().isOk());
//    }
//
//    public static String asJsonString(final AuthorDto obj) {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.registerModule(new JavaTimeModule());
//            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//            return mapper.writeValueAsString(obj);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
    }

