package com.wednesdaybeers.reversedraw.client.model;

import com.wednesdaybeers.reversedraw.client.service.TicketTransferService;

import java.security.SecureRandom;
import java.util.List;

public class Basket {
    private String serverURL;
    private TicketTransferService service;
    private int leftToDraw;
    private List<Ticket> tickets;
    private long seed;

    public Basket() {
        serverURL = System.getenv("REVERSE_DRAW_SERVER_URL");
        if (serverURL == null) {
            serverURL = "http://localhost:8080";
        }
        service = new TicketTransferService(serverURL);
        tickets = service.getTickets();
        leftToDraw = service.getNumParticipants();
    }

    public Ticket draw() {
        SecureRandom random = new SecureRandom();
        random.setSeed(seed + System.currentTimeMillis());
        int offset = random.nextInt(tickets.size());
        Ticket picked = tickets.remove(offset);
        service.removeTicket(picked);
        leftToDraw--;
        return picked;
    }

    public int ticketsLeft(String name) {
        int left = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getName().equals(name)) {
                left++;
            }
        }
        return left;
    }


    public int getNumTickets() {
        return tickets.size();
    }

    // Autogen setters and getters

    public List<Ticket> getTickets() {
        return tickets;
    }

    public int getLeftToDraw() {
        return leftToDraw;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }
}
