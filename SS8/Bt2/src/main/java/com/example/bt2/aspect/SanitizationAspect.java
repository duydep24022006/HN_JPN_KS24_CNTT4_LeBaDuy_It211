package com.example.bt2.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

public class SanitizationAspect {
    @Around("""
        execution(* com.example.bt2.service.TicketService.bookTicket(..))
    """)
    public Object sanitizePassengerName(
            ProceedingJoinPoint joinPoint
    ) throws Throwable {

        Object[] args = joinPoint.getArgs();

        String flightNumber = (String) args[0];

        String passengerName = (String) args[1];

        passengerName = passengerName
                .trim()
                .toUpperCase();

        args[1] = passengerName;

        return joinPoint.proceed(args);
    }
}
