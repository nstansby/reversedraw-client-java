package com.wednesdaybeers.reversedraw.client.dto.ticket;

import java.util.List;

public class TicketPostRequest {
    private List<String> names;
    private int numTickets;

    // Autogen setters and getters

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }
}
