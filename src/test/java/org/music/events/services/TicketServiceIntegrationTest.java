package org.music.events.integrationtests;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.qrcode.encoder.QRCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.music.events.models.*;
import org.music.events.repositories.EventRepository;
import org.music.events.repositories.TicketRepository;
import org.music.events.repositories.UserRepository;
import org.music.events.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)

public class TicketServiceIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;
    private User user;
    private Event event;
    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("testuser@example.com");
        userRepository.save(user);
        event = new Event();
        event.setEventName("Test Event");
        event.setEventType("festival");
        event.setEventPrice(100.0);
        event.setEventStartDate(LocalDate.of(2024, 6, 1));
        event.setEventEndDate(LocalDate.of(2024, 6, 3));
        eventRepository.save(event);
    }

    // In development
//    @Test
//    @WithMockUser(username = "admin", roles = "ADMIN")
//    public void purchaseTicket() throws Exception {
//        mockMvc.perform(post("/tickets/purchase")
//                        .param("eventId", event.getEventId().toString())
//                        .param("username", user.getUsername())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//        Ticket ticket = ticketRepository.findByQrCode(QRCodeImage, QRCodeImage);
//        assertNotNull(ticket);
//        assertEquals(TicketStatus.VALID, ticket.getStatus());
//    }
}