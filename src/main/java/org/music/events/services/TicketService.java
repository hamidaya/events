package org.music.events.services;
import org.music.events.dtos.TicketRequestDTO;
import org.music.events.dtos.TicketRespondsDTO;
import org.music.events.models.Event;
import org.music.events.repositories.EventRepository;
import org.music.events.repositories.TicketRepository;
import org.springframework.stereotype.Service;
import org.music.events.models.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    public TicketService(EventRepository eventRepository, TicketRepository ticketRepository) {
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
    }
    public List<TicketRespondsDTO> getAllTickets() {
        List<Ticket> ticketList = ticketRepository.findAll();
        return transferTicketListToDtoList(ticketList);
    }
    public List<TicketRespondsDTO> transferTicketListToDtoList(List<Ticket> tickets) {
        List<TicketRespondsDTO> ticketDtoList = new ArrayList<>();
        for (Ticket ticket : tickets) {
            TicketRespondsDTO dto = transferToDto(ticket);
            ticketDtoList.add(dto);
        }
        return ticketDtoList;
    }
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket transferToTicket(TicketRequestDTO dto) {
        Ticket ticket = new Ticket();
        Optional<Event> event = eventRepository.findById(dto.getEventId());
        ticket.setEvent(event.get());
        return ticket;
    }
    public TicketRespondsDTO transferToDto(Ticket ticket) {
        TicketRespondsDTO dto = new TicketRespondsDTO();
        dto.setEventId(ticket.getEvent().getEventId());
        dto.setAvailableTickets(ticket.getEvent().getAvailableTickets());

        return dto;
    }
}