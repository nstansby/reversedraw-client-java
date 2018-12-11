package com.wednesdaybeers.reversedraw.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.security.SecureRandom;

import static java.lang.Math.*;

public class RandomnessPanel extends JPanel implements MouseMotionListener {

    private static final int eventsRequired = 150;
    private SecureRandom random = new SecureRandom();
    private int events = 0;
    private long seed = random.nextLong();
    private Listener listener;
    private boolean finished = false;

    public RandomnessPanel(Listener listener) {
        this.listener = listener;
        addMouseMotionListener(this);
    }

    public void finished() {
        finished = true;
        this.removeMouseMotionListener(this);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int where = getWidth() * min(events, eventsRequired) / eventsRequired;
        g2.setBackground(Color.LIGHT_GRAY);
        g2.clearRect(0, 0, getWidth(), getHeight());
        Color color = finished ? Color.LIGHT_GRAY : events < eventsRequired ? Color.RED : new Color(0, 180, 0);
        g2.setColor(color);
        g2.fillRect(0, 0, where, getHeight());
        g2.setColor(Color.BLACK);
        String message = finished ? "Done for today." : events < eventsRequired ? "Move mouse around here." : "That mixed it up!";
        g2.drawString(message, 10, (getHeight() / 2) + 6);
    }

    // MouseMotionListener interface implementation

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double frac = ((double) e.getX() / getWidth()) * ((double) e.getY() / getHeight()) + 0.001;
        seed += round(Integer.MAX_VALUE * frac) + random.nextLong();
        if (events++ == eventsRequired) {
            listener.ready(seed);
        };
        repaint();
    }

    public void reset() {
        events = 0;
    }

    // Listener class

    public interface Listener {
        void ready(long seed);
    }
}
