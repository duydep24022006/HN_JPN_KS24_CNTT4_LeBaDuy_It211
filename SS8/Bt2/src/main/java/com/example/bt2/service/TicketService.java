package com.example.bt2.service;

import com.example.bt2.model.entity.Ticket;

public interface TicketService {

    Ticket bookTicket(String flightNumber, String passengerName);

    void cancelTicket(Long ticketId);
}
