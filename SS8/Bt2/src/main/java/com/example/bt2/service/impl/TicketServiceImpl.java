package com.example.bt2.service.impl;

import com.example.bt2.model.entity.Flight;
import com.example.bt2.model.entity.Ticket;
import com.example.bt2.repository.FlightRepository;
import com.example.bt2.repository.TicketRepository;
import com.example.bt2.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Fidelity;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final FlightRepository flightRepository;
    private final TicketRepository ticketRepository;

    @Override
    public Ticket bookTicket(String flightNumber, String passengerName) {
        Flight flight =flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy chuyến bay"));
        if (flight.getAvailableSeats()<=0){
            throw new RuntimeException("Hết chỗ trên chuyến bay");
        }
        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightRepository.save(flight);
        Ticket ticket = Ticket.builder()
                .flightId(flight.getId())
                .passengerName(passengerName)
                .status("BOOKED")
                .build();
        return ticketRepository.save(ticket);
    }


    @Override
    public void cancelTicket(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vé"));

        ticket.setStatus("CANCELED");

        ticketRepository.save(ticket);
    }
}
