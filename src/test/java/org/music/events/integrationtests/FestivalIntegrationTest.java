package org.music.events.integrationtests;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.music.events.dtos.EventRespondsDTO;
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

        festivalRespondsDTO = new FestivalRespondsDTO(1L, "mark festival", "festival", "utrecht",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 56.00, 34, "dit festival alleen voor mark en frans", "mark Artist", true);
        festivalRequestDTO = new FestivalRequestDTO("mark festival", "festival", "utrecht",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15), 56.00, 34, "dit festival alleen voor mark en frans", "mark Artist", true);

        festivalRequestDTO = new FestivalRequestDTO("mark festival", "festival", "amsterdam",
                LocalDate.of(2024, 5, 10), LocalDate.of(2024, 5, 20), 75.00, 100, "nieuw festival in amsterdam", "mark Artist", true);
        festivalService.addFestival(festivalRequestDTO);
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    void getAllFestivals() throws Exception {
        mockMvc.perform(get("/festivals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].eventName").value("mark festival"))
                .andExpect(jsonPath("$[0].eventType").value("festival"))
                .andExpect(jsonPath("$[0].eventLocation").value("amsterdam"))
                .andExpect(jsonPath("$[0].eventStartDate").value("2024-05-10"))
                .andExpect(jsonPath("$[0].eventEndDate").value("2024-05-20"))
                .andExpect(jsonPath("$[0].availableTickets").value(100))
                .andExpect(jsonPath("$[0].eventPrice").value(75.00))
                .andExpect(jsonPath("$[0].eventDescription").value("nieuw festival in amsterdam"))
                .andExpect(jsonPath("$[0].artistName").value("mark Artist"))
                .andExpect(jsonPath("$[0].campingAvailable").value(true));
    }
    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    void addFestival() throws Exception {
        mockMvc.perform(post("/festivals").with(httpBasic("admin", "password"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(festivalRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eventName").value("mark festival"))
                .andExpect(jsonPath("$.eventType").value("festival"))
                .andExpect(jsonPath("$.eventLocation").value("amsterdam"))
                .andExpect(jsonPath("$.eventStartDate").value("2024-05-10"))
                .andExpect(jsonPath("$.eventEndDate").value("2024-05-20"))
                .andExpect(jsonPath("$.availableTickets").value(100))
                .andExpect(jsonPath("$.eventPrice").value(75.00))
                .andExpect(jsonPath("$.eventDescription").value("nieuw festival in amsterdam"))
                .andExpect(jsonPath("$.artistName").value("mark Artist"))
                .andExpect(jsonPath("$.campingAvailable").value(true));
    }


    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    void updateFestival() throws Exception {
        FestivalRespondsDTO addedFestival = festivalService.addFestival(festivalRequestDTO);
        Long eventId = addedFestival.getEventId();
        FestivalRequestDTO updatedFestivalRequestDTO = new FestivalRequestDTO("mark party updated", "party","amsterdam",
                LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 15),100.00,100,"updated description", "updated description",true);
        mockMvc.perform(put("/festivals/{eventId}", eventId).with(httpBasic("admin", "password"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedFestivalRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eventName").value("mark party updated"))
                .andExpect(jsonPath("$.eventType").value("party"))
                .andExpect(jsonPath("$.eventLocation").value("amsterdam"))
                .andExpect(jsonPath("$.eventStartDate").value("2024-02-04"))
                .andExpect(jsonPath("$.eventEndDate").value("2024-07-15"))
                .andExpect(jsonPath("$.availableTickets").value(100))
                .andExpect(jsonPath("$.eventPrice").value(100.00))
                .andExpect(jsonPath("$.eventDescription").value("updated description"))
                .andExpect(jsonPath("$.artistName").value("mark Artist"))
                .andExpect(jsonPath("$.campingAvailable").value(true));
    }

//    @Test
//    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
//    void updateFestival() throws Exception {
//        mockMvc.perform(put("/festivals/").with(httpBasic("admin", "password"))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//
//        {
//
//            "eventName": "supersupergeengeen geen fffffFestival",
//                "eventType": "festival",
//                "eventLocation": "nergens",
//                "eventStartDate": "2024-05-11",
//                "eventEndDate": "2024-05-14",
//                "eventPrice": 11150,
//                "availableTickets": 12200,
//                "eventDescription": "echt leuke festival",
//                "artistName": "mega artist",
//                "campingAvailable":true
////        }
////    """))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.eventId").value(1L))
//                .andExpect(jsonPath("$.eventName").value("mark festival updated"))
//                .andExpect(jsonPath("$.eventType").value("festival"))
//                .andExpect(jsonPath("$.eventLocation").value("utrecht"))
//                .andExpect(jsonPath("$.eventStartDate").value("2024-02-04"))
//                .andExpect(jsonPath("$.eventEndDate").value("2024-07-15"))
//                .andExpect(jsonPath("$.availableTickets").value(456))
//                .andExpect(jsonPath("$.eventPrice").value(34.80))
//                .andExpect(jsonPath("$.eventDescription").value("dit festival alleen voor mark en frans"))
//                .andExpect(jsonPath("$.artistName").value("mark Artist"))
//                .andExpect(jsonPath("$.campingAvailable").value(false));
//    }

    @Test
    void deleteFestival() throws Exception {
        EventRespondsDTO addedEvent = festivalService.addFestival(festivalRequestDTO);
        Long eventId = addedEvent.getEventId();
        mockMvc.perform(delete("/festivals/{eventId}", eventId))
                .andExpect(status().isNoContent());

    }
}
