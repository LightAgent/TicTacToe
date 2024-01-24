package view;

import javax.swing.*;

import controller.Controller;
import model.XOButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame{
    private static final int SQUARE_DIMENSION = 150;
    public Controller controller;

    public GameWindow(Controller controller) {
        this.controller = controller;
        initializeWindow();
    }

    private void initializeWindow() {
        setTitle("Grid of Buttons");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        // Create buttons and add ActionListener
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new XOButton(i,j);
                ImageIcon imageIcon = new ImageIcon("images\\default.png");
                Image img = imageIcon.getImage().getScaledInstance(SQUARE_DIMENSION, SQUARE_DIMENSION, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(img);
                button.setIcon(scaledIcon);
                button.setPreferredSize(new Dimension(150, 150));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                    }
                });
                add(button);
            }
        }

        pack();
        setLocationRelativeTo(null); // Center the JFrame
    }
}
