package org.music.events.integrationtests;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import java.util.List;

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
    private FestivalService festivalService;
    @Autowired
    private FestivalRepository festivalRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private FestivalRespondsDTO festivalRespondsDTO;
    private FestivalRequestDTO festivalRequestDTO;
    @BeforeEach
    public void setUp() {

        festivalRepository.deleteAll();

        festivalRespondsDTO = new FestivalRespondsDTO(3L, "mark festival", "festival", "utrecht",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 56.00, 34, "dit festival alleen voor mark en frans", "Mark Artist", true);
        festivalRequestDTO = new FestivalRequestDTO("mark festival", "festival", "utrecht",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 56.00, 34, "dit festival alleen voor mark en frans", "Mark Artist", true);

        festivalService.addFestival(festivalRequestDTO);
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    void getAllFestivals() throws Exception {
        mockMvc.perform(get("/festivals").with(httpBasic("admin", "password"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].festivalId").value(1L))
                .andExpect(jsonPath("$[0].festivalName").value("mark festival"))
                .andExpect(jsonPath("$[0].festivalType").value("festival"))
                .andExpect(jsonPath("$[0].festivalLocation").value("utrecht"))
                .andExpect(jsonPath("$[0].festivalStartDate").value("2024-02-04"))
                .andExpect(jsonPath("$[0].festivalEndDate").value("2024-07-15"))
                .andExpect(jsonPath("$[0].availableTickets").value(34))
                .andExpect(jsonPath("$[0].festivalPrice").value(56.00))
                .andExpect(jsonPath("$[0].festivalDescription").value("dit festival alleen voor mark en frans"))
                .andExpect(jsonPath("$[0].artistName").value("Mark Artist"))
                .andExpect(jsonPath("$[0].campingAvailable").value(true));
    }
    @Test
    void addFestival() throws Exception {
        FestivalRequestDTO newFestivalRequestDTO = new FestivalRequestDTO("new festival", "festival", "amsterdam",
                LocalDate.of(2024, 5, 10), LocalDate.of(2024, 5, 20), 75.00, 100, "nieuw festival in amsterdam", "New Artist", false);
        mockMvc.perform(post("/festivals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newFestivalRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.festivalName").value("new festival"))
                .andExpect(jsonPath("$.festivalType").value("festival"))
                .andExpect(jsonPath("$.festivalLocation").value("amsterdam"))
                .andExpect(jsonPath("$.festivalStartDate").value("2024-05-10"))
                .andExpect(jsonPath("$.festivalEndDate").value("2024-05-20"))
                .andExpect(jsonPath("$.availableTickets").value(100))
                .andExpect(jsonPath("$.festivalPrice").value(75.00))
                .andExpect(jsonPath("$.festivalDescription").value("nieuw festival in amsterdam"))
                .andExpect(jsonPath("$.artistName").value("New Artist"))
                .andExpect(jsonPath("$.campingAvailable").value(false));
    }
    @Test
    void updateFestival() throws Exception {
        FestivalRequestDTO updatedFestivalRequestDTO = new FestivalRequestDTO("mark festival updated", "festival", "utrecht",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 34.80, 456, "dit festival alleen voor mark en frans", "Updated Artist", false);
        mockMvc.perform(put("/festivals/{festivalId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedFestivalRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.festivalId").value(1L))
                .andExpect(jsonPath("$.festivalName").value("mark festival updated"))
                .andExpect(jsonPath("$.festivalType").value("festival"))
                .andExpect(jsonPath("$.festivalLocation").value("utrecht"))
                .andExpect(jsonPath("$.festivalStartDate").value("2024-02-04"))
                .andExpect(jsonPath("$.festivalEndDate").value("2024-07-15"))
                .andExpect(jsonPath("$.availableTickets").value(456))
                .andExpect(jsonPath("$.festivalPrice").value(34.80))
                .andExpect(jsonPath("$.festivalDescription").value("dit festival alleen voor mark en frans"))
                .andExpect(jsonPath("$.artistName").value("Updated Artist"))
                .andExpect(jsonPath("$.campingAvailable").value(false));
    }
    @Test
    void deleteFestival() throws Exception {
        mockMvc.perform(delete("/festivals/{festivalId}", 1L))
                .andExpect(status().isNoContent());
    }
    }