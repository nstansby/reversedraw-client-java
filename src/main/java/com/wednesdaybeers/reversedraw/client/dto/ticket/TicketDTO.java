package com.wednesdaybeers.reversedraw.client.dto.ticket;


import com.wednesdaybeers.reversedraw.client.model.Ticket;

public class TicketDTO {
    private int id;
    private Ticket.State state;
    private String name;

    // Autogen setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket.State getState() {
        return state;
    }

    public void setState(Ticket.State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
