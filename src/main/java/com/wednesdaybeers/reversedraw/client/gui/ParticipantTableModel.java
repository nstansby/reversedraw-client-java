package com.wednesdaybeers.reversedraw.client.gui;

import com.wednesdaybeers.reversedraw.client.model.Ticket;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class ParticipantTableModel extends DefaultTableModel {
    private List<Ticket> tickets;
    private List<String> names;
    private TreeMap<String, Integer> counts = new TreeMap<>();

    public ParticipantTableModel(List<Ticket> tickets) {
        super(0, 2);
        this.tickets = tickets;
        update();
    }

    /**
     * Updates the internal data structure based on the current setting of the tickets array
     */
    public void update() {
        counts.clear();
        for (Ticket ticket : tickets) {
            if (counts.containsKey(ticket.getName())) {
                counts.put(ticket.getName(), counts.get(ticket.getName()) + 1);
            } else {
                counts.put(ticket.getName(), 1);
            }
        }

        names = new ArrayList<>(counts.keySet());
        Collections.sort(names);
    }

    @Override
    public int getRowCount() {
        if (names == null) {
            return 0;
        }
        return names.size();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Name";
            case 1:
                return "Tickets Remaining";
            default:
                throw new RuntimeException("Illegal column number " + column);
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        String name = names.get(row);
        switch (column) {
            case 0:
                return name;
            case 1:
                return counts.get(name);
            default:
                throw new RuntimeException("Illegal column number " + column);
        }
    }
}
