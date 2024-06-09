package org.music.events.integrationtests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.music.events.dtos.FestivalRequestDTO;
import org.music.events.dtos.FestivalRespondsDTO;
import org.music.events.models.Festival;
import org.music.events.repositories.FestivalRepository;
import org.music.events.services.FestivalService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class FestivalIntegrationTest {

    private FestivalService festivalService;

    @Mock
    private FestivalRepository festivalRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        festivalService = new FestivalService(festivalRepository);
    }

    @Test
    void deleteFestival() {
        // Arrange
        Festival festival1 = new Festival();
        festival1.setEventName("Festival 1");
        Festival festival2 = new Festival();
        festival2.setEventName("Festival 2");
        List<Festival> festivalList = List.of(festival1, festival2);

        when(festivalRepository.findAll()).thenReturn(festivalList);

        // Act
        List<FestivalRespondsDTO> result = festivalService.getAllFestivals();

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getEventName()).isEqualTo("Festival 1");
        assertThat(result.get(1).getEventName()).isEqualTo("Festival 2");
    }

    @Test
    void addFestival() {
        // Arrange
        FestivalRequestDTO requestDTO = new FestivalRequestDTO(
                "New Festival",
                "Festival",
                "Utrecht",
                LocalDate.of(2024, 2, 4),
                LocalDate.of(2024, 7, 15),
                56.00,
                34,
                "Description",
                "Artist",
                true
        );

        when(festivalRepository.save(any(Festival.class))).thenReturn(new Festival());

        // Act
        FestivalRespondsDTO result = festivalService.addFestival(requestDTO);

        // Assert
        assertThat(result.getEventName()).isEqualTo("New Festival");
        verify(festivalRepository, times(1)).save(any(Festival.class));
    }

    // Voeg meer testmethoden toe voor andere functionaliteiten (bijwerken, verwijderen, etc.)

    // Voorbeeldtest voor de updateFestival-methode
    @Test
    void updateFestival() {
        // Arrange
        Long eventId = 1L;
        FestivalRequestDTO requestDTO = new FestivalRequestDTO(
                "Updated Festival",
                "Festival",
                "Amsterdam",
                LocalDate.of(2024, 3, 10),
                LocalDate.of(2024, 7, 20),
                60.00,
                50,
                "Updated Description",
                "New Artist",
                false
        );
        Festival existingFestival = new Festival();
        existingFestival.setEventId(eventId);

        when(festivalRepository.findById(eventId)).thenReturn(Optional.of(existingFestival));
        when(festivalRepository.save(any(Festival.class))).thenReturn(existingFestival);

        // Act
        Festival updatedFestival = festivalService.updateFestival(eventId, requestDTO);

        // Assert
        assertThat(updatedFestival.getEventName()).isEqualTo("Updated Festival");
        assertThat(updatedFestival.getEventLocation()).isEqualTo("Amsterdam");
        verify(festivalRepository, times(1)).save(any(Festival.class));
    }

}

