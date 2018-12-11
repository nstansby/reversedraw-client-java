package com.wednesdaybeers.reversedraw.client;

import com.wednesdaybeers.reversedraw.client.gui.MainGUI;
import com.wednesdaybeers.reversedraw.client.model.Basket;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ClientApplication {

    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        Basket basket = new Basket();

        JFrame frame = new JFrame("WineDraw");
        frame.setMinimumSize(new Dimension(1000,700));
        frame.setContentPane(new MainGUI(basket).getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}