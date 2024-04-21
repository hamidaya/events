package org.music.events.services;
import org.music.events.dtos.FestivalRequestDTO;
import org.music.events.dtos.FestivalRespondsDTO;
import org.music.events.models.Festival;
import org.music.events.repositories.FestivalRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class FestivalService {
    private final FestivalRepository festivalRepository;
    public FestivalService(FestivalRepository festivalRepository) {
        this.festivalRepository = festivalRepository;
    }
    public List<FestivalRespondsDTO> getAllFestivals() {
        List<Festival> festivalList = festivalRepository.findAll();
        return transferFestivalListToDtoList(festivalList);
    }
    public List<FestivalRespondsDTO> transferFestivalListToDtoList(List<Festival> festivals) {
        List<FestivalRespondsDTO> festivalDtoList = new ArrayList<>();
        for (Festival festival : festivals) {
            FestivalRespondsDTO dto = transferToDto(festival);
            if (festival.getEventName() != null) {
                dto.setEventName(festival.getEventName());
            }
            if (festival.getEventLocation() != null) {
                dto.setEventLocation(festival.getEventLocation());
            }
            festivalDtoList.add(dto);
        }
        return festivalDtoList;
    }
    public FestivalRespondsDTO addFestival(FestivalRequestDTO dto) {
        Festival festival = transferToEvent(dto);
        festivalRepository.save(festival);
        return transferToDto(festival);
    }
    public Festival updateFestival(Long festivalId, Festival updatedFestival) {
        // Implement updating festival
        return updatedFestival;
    }
    public void deleteFestival(Long festivalId) {
        // Implement deleting festival
    }
    public Festival transferToEvent(FestivalRequestDTO dto) {
        Festival festival = new Festival();
        festival.setEventLocation(dto.getEventLocation());
        festival.setEventName(dto.getEventName());
        festival.setEventPrice(dto.getEventPrice());
        festival.setEventStartDate(dto.getEventStartDate());
        festival.setEventEndDate(dto.getEventEndDate());
        festival.setAvailableTickets(dto.getAvailableTickets());
        festival.setEventType(dto.getEventType());
        festival.setEventDescription(dto.getEventDescription());
        festival.setArtistName(dto.getArtistName());
        festival.setCampingAvailable(dto.getCampingAvailable());
        return festival;
    }
    public FestivalRespondsDTO transferToDto(Festival festival) {
        FestivalRespondsDTO dto = new FestivalRespondsDTO();
        dto.setEventName(festival.getEventName());
        dto.setEventLocation(festival.getEventLocation());
        dto.setEventPrice(festival.getEventPrice());
        dto.setEventStartDate(festival.getEventStartDate());
        dto.setEventEndDate(festival.getEventEndDate());
        dto.setAvailableTickets(festival.getAvailableTickets());
        dto.setEventType(festival.getEventType());
        dto.setEventDescription(festival.getEventDescription());
        dto.setArtistName(festival.getArtistName());
        dto.setCampingAvailable(festival.getCampingAvailable());
        return dto;
    }
}