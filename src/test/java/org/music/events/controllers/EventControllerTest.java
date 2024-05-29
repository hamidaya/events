package org.music.events.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.music.events.models.Event;
import org.music.events.services.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService bookService;

    Event event1;
    Event event2;
    Event event3;

    @BeforeEach
    public void setUp() {
//        author1 = new Author(1L ,"J.K.", "Joanne Kathleen", "Rowling", LocalDate.of(1965,7,31), Gender.FEMALE);
//        author2 = new Author(2L,"R.", "Roald", "Dahl", LocalDate.of(1916,9,13), Gender.MALE);

        event1 = new Event(1L,"mark event", "party","utrecht", LocalDate.of(2024, 02, 04),LocalDate.of(2024,07,15),34,456,"dit event alleen voor mark en frans");
        event2 = new Event("9781781103470", "Harry Potter", "en de geheime kamer", "fiction", "NL", "paperback", "Pottermore publishing", author1);
        event3 = new Event("9789026139406", "Mathilda", "", "fiction", "NL", "paperback", "de Fontein Jeugd", author2);

        book1 = new Book("9789076174105", "Harry Potter", "en de steen der wijzen", "fiction", "NL", "paperback", "uitgeverij de Harmonie", author1);
        book2 = new Book("9781781103470", "Harry Potter", "en de geheime kamer", "fiction", "NL", "paperback", "Pottermore publishing", author1);
        book3 = new Book("9789026139406", "Mathilda", "", "fiction", "NL", "paperback", "de Fontein Jeugd", author2);

        author1 = new Author(1L ,"J.K.", "Joanne Kathleen", "Rowling", LocalDate.of(1965,7,31), Gender.FEMALE);
        author2 = new Author(2L,"R.", "Roald", "Dahl", LocalDate.of(1916,9,13), Gender.MALE);

//
//
//        private Long eventId;
//        private String eventName;
//        private String eventType;
//        private String eventLocation;
//        private LocalDate eventStartDate;
//        private LocalDate eventEndDate;
//        private Double eventPrice;
//        private Integer availableTickets;
//        private String eventDescription;






        @AfterEach
    void tearDown() {
    }

    @Test
    void getAllEvents() {
    }

    @Test
    void addEvent() {
    }
}