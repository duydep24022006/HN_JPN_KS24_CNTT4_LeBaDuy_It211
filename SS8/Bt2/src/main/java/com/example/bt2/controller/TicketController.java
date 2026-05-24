package com.example.bt2.controller;

import com.example.bt2.model.dto.request.BookTicketRequest;
import com.example.bt2.model.entity.Ticket;
import com.example.bt2.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/book")
    public Ticket bookTicket(
            @Valid @RequestBody BookTicketRequest request
    ) {

        return ticketService.bookTicket(
                request.getFlightNumber(),
                request.getPassengerName()
        );
    }

    @PostMapping("/cancel/{ticketId}")
    public String cancelTicket(
            @PathVariable Long ticketId
    ) {

        ticketService.cancelTicket(ticketId);

        return "Hủy vé thành công";
    }
}
