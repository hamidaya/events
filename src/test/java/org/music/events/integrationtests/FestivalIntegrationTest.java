package org.music.events.integrationtests;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.music.events.dtos.EventRequestDTO;
import org.music.events.dtos.FestivalRequestDTO;
import org.music.events.dtos.FestivalRespondsDTO;
import org.music.events.models.Festival;
import org.music.events.repositories.FestivalRepository;
import org.music.events.services.FestivalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class FestivalIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private FestivalService festivalService;
    @Autowired
    private FestivalRepository festivalRepository;
    private FestivalRequestDTO festivalRequestDTO;
    private Festival festival1;
    @BeforeEach
    public void setUp() {
        festivalRepository.deleteAll();
        festival1 = new Festival();
        festival1 = festivalRepository.save(festival1);
        festivalRequestDTO = new FestivalRequestDTO(
                "mark festival",
                "festival",
                "utrecht",
                LocalDate.of(2024, 2, 4),
                LocalDate.of(2024, 7, 15),
                56.00,
                34,
                "dit festival alleen voor mark en frans","hamid",true
        );
    }
    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    void getAllFestivals() throws Exception {
        mockMvc.perform(get("/festivals")
//                        .with(httpBasic("admin", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].eventName").value("mark festival"))
                .andExpect(jsonPath("$[0].eventType").value("festival"))
                .andExpect(jsonPath("$[0].eventLocation").value("utrecht"))
                .andExpect(jsonPath("$[0].eventStartDate").value("2024-02-04"))
                .andExpect(jsonPath("$[0].eventEndDate").value("2024-07-15"))
                .andExpect(jsonPath("$[0].availableTickets").value(34))
                .andExpect(jsonPath("$[0].eventPrice").value(56.00))
                .andExpect(jsonPath("$[0].eventDescription").value("dit festival alleen voor mark en frans"))
                .andExpect(jsonPath("$[0].artistName").value("Mark Artist"))
                .andExpect(jsonPath("$[0].campingAvailable").value(true));
    }
    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    void addFestival() throws Exception {
        mockMvc.perform(post("/festivals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(festivalRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eventName").value("mark festival"))
                .andExpect(jsonPath("$.eventType").value("festival"))
                .andExpect(jsonPath("$.eventLocation").value("utrecht"))
                .andExpect(jsonPath("$.eventStartDate").value("2024-02-04"))
                .andExpect(jsonPath("$.eventEndDate").value("2024-07-15"))
                .andExpect(jsonPath("$.availableTickets").value(34))
                .andExpect(jsonPath("$.eventPrice").value(56.00))
                .andExpect(jsonPath("$.eventDescription").value("dit festival alleen voor mark en frans"))
                .andExpect(jsonPath("$.artistName").value("Mark Artist"))
                .andExpect(jsonPath("$.campingAvailable").value(true));
    }
    @Test
    void updateFestival() throws Exception {
        FestivalRequestDTO updatedFestivalRequestDTO = new FestivalRequestDTO(
                "mark festival updated",
                "festival",
                "utrecht",
                LocalDate.of(2024, 2, 4),
                LocalDate.of(2024, 7, 15),
                456.00,
                34,
                "dit festival alleen voor mark en frans", "hamid",true
        );
        mockMvc.perform(put("/{eventId}", festival1.getEventId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedFestivalRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eventId").value(festival1.getEventId()))
                .andExpect(jsonPath("$.eventName").value("mark festival updated"))
                .andExpect(jsonPath("$.eventType").value("festival"))
                .andExpect(jsonPath("$.eventLocation").value("utrecht"))
                .andExpect(jsonPath("$.eventStartDate").value("2024-02-04"))
                .andExpect(jsonPath("$.eventEndDate").value("2024-07-15"))
                .andExpect(jsonPath("$.availableTickets").value(34))
                .andExpect(jsonPath("$.eventPrice").value(456.00))
                .andExpect(jsonPath("$.eventDescription").value("dit festival alleen voor mark en frans"))
                .andExpect(jsonPath("$.artistName").value("Mark Artist"))
                .andExpect(jsonPath("$.campingAvailable").value(true));
    }
    @Test
    void deleteFestival() throws Exception {
        mockMvc.perform(delete("/festivals/{eventId}", festival1.getEventId()))
                .andExpect(status().isNoContent());
    }
}