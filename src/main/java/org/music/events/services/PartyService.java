package org.music.events.services;

import jakarta.persistence.EntityNotFoundException;
import org.music.events.dtos.PartyRequestDTO;
import org.music.events.dtos.PartyRespondsDTO;
import org.music.events.models.Party;
import org.music.events.repositories.PartyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class PartyService {

    private final PartyRepository partyRepository;
    public PartyService(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }
    public List<PartyRespondsDTO> getAllPartys() {
        List<Party> partyList = partyRepository.findAll();
        return transferPartyListToDtoList(partyList);
    }
    public List<PartyRespondsDTO> transferPartyListToDtoList(List<Party> partys) {
        List<PartyRespondsDTO> partyDtoList = new ArrayList<>();
        for (Party party : partys) {
            PartyRespondsDTO dto = transferToDto(party);
            if (party.getEventName() != null) {
                dto.setEventName(party.getEventName());
            }
            if (party.getEventLocation() != null) {
                dto.setEventLocation(party.getEventLocation());
            }
            partyDtoList.add(dto);
        }
        return partyDtoList;
    }
    public PartyRespondsDTO addParty(PartyRequestDTO dto) {
        Party party = transferToEvent(dto);
        partyRepository.save(party);
        return transferToDto(party);
    }


    public Party transferToEvent(PartyRequestDTO dto) {
        Party party = new Party();
        party.setEventLocation(dto.getEventLocation());
        party.setEventType(dto.getEventType());
        party.setEventName(dto.getEventName());
        party.setEventPrice(dto.getEventPrice());
        party.setEventStartDate(dto.getEventStartDate());
        party.setEventEndDate(dto.getEventEndDate());
        party.setAvailableTickets(dto.getAvailableTickets());
        party.setEventDescription(dto.getEventDescription());
        party.setDjName(dto.getDjName());
        party.setDressCode(dto.getDressCode());
        return party;
    }
    public PartyRespondsDTO transferToDto(Party party) {
        PartyRespondsDTO dto = new PartyRespondsDTO();
        dto.setEventId(party.getEventId());
        dto.setEventName(party.getEventName());
        dto.setEventType(party.getEventType());
        dto.setEventLocation(party.getEventLocation());
        dto.setEventPrice(party.getEventPrice());
        dto.setEventStartDate(party.getEventStartDate());
        dto.setEventEndDate(party.getEventEndDate());
        dto.setAvailableTickets(party.getAvailableTickets());
        dto.setEventType(party.getEventType());
        dto.setEventDescription(party.getEventDescription());
        dto.setDjName(party.getDjName());
        dto.setDressCode(party.getDressCode());
        return dto;
    }

    //Update party methode.
    public Party updateParty(Long eventId, PartyRequestDTO partyRequestDTO) {
        Party partyToUpdate = partyRepository.findById(eventId).orElseThrow();
        new EntityNotFoundException("Party with id " + eventId + " not found");

        partyToUpdate.setEventName(partyRequestDTO.getEventName());
        partyToUpdate.setEventLocation(partyRequestDTO.getEventLocation());
        partyToUpdate.setEventType(partyRequestDTO.getEventType());
        partyToUpdate.setEventStartDate(partyRequestDTO.getEventStartDate());
        partyToUpdate.setEventEndDate(partyRequestDTO.getEventEndDate());
        partyToUpdate.setEventPrice(partyRequestDTO.getEventPrice());
        partyToUpdate.setEventDescription(partyRequestDTO.getEventDescription());

        return partyRepository.save(partyToUpdate);

    }

    public void deleteParty(Long eventId) {
        partyRepository.deleteById(eventId);
        new EntityNotFoundException("Event with id " + eventId + "deleted successfully");


    }

}