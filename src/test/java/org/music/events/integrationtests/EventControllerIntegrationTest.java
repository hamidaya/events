package org.music.events.integrationtests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.music.events.dtos.EventRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@SpringBootTest
    @AutoConfigureMockMvc
    public class EventControllerIntegrationTest {
        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private ObjectMapper objectMapper;
        @Test
        @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
        void addEvent() throws Exception {
            // Test with a valid EventRequestDTO
            EventRequestDTO eventRequestDTO = new EventRequestDTO();
            eventRequestDTO.setEventName("mark party");
            eventRequestDTO.setEventType("party");
            eventRequestDTO.setEventLocation("amsterdam");
            eventRequestDTO.setEventStartDate(LocalDate.parse("2024-02-04"));
            eventRequestDTO.setEventEndDate(LocalDate.parse("2024-07-15"));
            eventRequestDTO.setAvailableTickets(34);
            eventRequestDTO.setEventPrice(5556.00);
            eventRequestDTO.setEventDescription("dit event alleen voor mark en frans");
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

            mockMvc.perform(post("/events")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}"))
                    .andExpect(status().isBadRequest());
        }
    }

