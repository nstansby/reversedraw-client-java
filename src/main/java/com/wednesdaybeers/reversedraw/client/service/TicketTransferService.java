package com.wednesdaybeers.reversedraw.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.wednesdaybeers.reversedraw.client.dto.ticket.TicketGetResponse;
import com.wednesdaybeers.reversedraw.client.dto.ticket.TicketDTO;
import com.wednesdaybeers.reversedraw.client.model.Ticket;

import java.io.IOException;
import java.util.List;

public class TicketTransferService {
    private String serverURL;
    private int numParticipants = -1;

    public TicketTransferService(String serverURL) {
        this.serverURL = serverURL;

        // Set up UniREST to use a Jackson object mapper.
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public List<Ticket> getTickets() throws TicketServiceException {
        try {
            HttpResponse<TicketGetResponse> httpResponse = Unirest.get(serverURL + "/api/v1/tickets").asObject(TicketGetResponse.class);

            if (httpResponse.getStatus() != 200) {
                throw new TicketServiceException("Unexpected response " + httpResponse.getStatus() + " from server");
            }

            numParticipants = httpResponse.getBody().getNumParticipants();

            return httpResponse.getBody().getTickets();

        } catch (UnirestException ex) {
            throw new TicketServiceException(ex.toString());
        }
    }

    public int getNumParticipants() throws TicketServiceException {
        if (numParticipants == -1) { // We haven't cached it yet so get it explicitly
            getTickets();
        }
        return numParticipants;
    }

    public void removeTicket(Ticket ticket) throws TicketServiceException {
        try {
            HttpResponse<TicketDTO> httpResponse = Unirest.delete(serverURL + "/api/v1/tickets").field("id", ticket.getId()).asObject(TicketDTO.class);
        } catch (UnirestException ex) {
            throw new TicketServiceException(ex.toString());
        }
    }
}
