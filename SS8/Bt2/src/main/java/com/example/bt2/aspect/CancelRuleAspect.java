package com.example.bt2.aspect;

import com.example.bt2.model.entity.Flight;
import com.example.bt2.model.entity.Ticket;
import com.example.bt2.repository.FlightRepository;
import com.example.bt2.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class CancelRuleAspect {

    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;

    @Before("""
        execution(* com.example.bt2.service.TicketService.cancelTicket(..))
        && args(ticketId)
    """)
    public void checkCancelRule(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vé"));

        Flight flight = flightRepository.findById(ticket.getFlightId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyến bay"));

        long hours = Duration.between(
                LocalDateTime.now(),
                flight.getDepartureTime()
        ).toHours();

        if (hours < 24) {
            throw new RuntimeException(
                    "Không được hủy vé trước giờ bay dưới 24h"
            );
        }
    }
}
