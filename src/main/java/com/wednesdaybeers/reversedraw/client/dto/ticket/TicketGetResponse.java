package com.wednesdaybeers.reversedraw.client.dto.ticket;

import com.wednesdaybeers.reversedraw.client.model.Ticket;

import java.util.List;

public class TicketGetResponse {

    private int numParticipants;
    private List<Ticket> tickets;

    // Autogen setters / getters

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getNumParticipants() {
        return numParticipants;
    }

    public void setNumParticipants(int numParticipants) {
        this.numParticipants = numParticipants;
    }
}
