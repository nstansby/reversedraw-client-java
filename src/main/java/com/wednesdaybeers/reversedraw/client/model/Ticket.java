package com.wednesdaybeers.reversedraw.client.model;


public class Ticket {

    public enum State {
        ACTIVE,
        DRAWN
    }

    private int id;
    private State state;
    private String name;

    // Autogen setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
