package view;

import javax.swing.*;

import controller.Controller;
import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private static final int SQUARE_DIMENSION = 150;
    private Controller controller;
    private JButton undoButton = new JButton("Undo");
    private XOButton[][] xoButtons;
    private boolean gameEnded;

    public GameWindow(Controller controller) {
        this.controller = controller;
        this.xoButtons = new XOButton[3][3];
        initializeWindow();
        initializeUndoButton();
    }

    private void initializeUndoButton() {
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                controller.undoLastMove();
                redrawBoard(controller.getBoard());
            }
        });
    }

    private void redrawBoard(XOBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ImageIcon imageIcon = FlyweightFactory.createImage(board.getBoard()[i][j].getMarkerType());
                xoButtons[i][j].setIcon(imageIcon);
            }
        }
    }

    private void initializeWindow() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create buttons and add ActionListener
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                XOButton button = new XOButton(i, j);
                ImageIcon imageIcon = new ImageIcon("images\\default.png");
                Image img = imageIcon.getImage().getScaledInstance(SQUARE_DIMENSION, SQUARE_DIMENSION, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(img);
                button.setIcon(scaledIcon);
                button.setPreferredSize(new Dimension(150, 150));
                button.setBackground(new Color(239, 224, 187));
                xoButtons[i][j] = button;

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        if (gameEnded) return;
                        XOButton clickedButton = (XOButton) event.getSource();
                        XOButton button = xoButtons[clickedButton.getPosition().getX()][clickedButton.getPosition().getY()];
                        if (!controller.getMarker(button.getPosition()).getMarkerType().equals(MarkerType.EMPTY))
                            return;
                        GameState state = controller.play(clickedButton.getPosition());
                        redrawBoard(controller.getBoard());
                        if (state == GameState.WON) {
                            JOptionPane.showMessageDialog(null, controller.getCurrentTurn() == MarkerType.X ? "X Won" : "O Won", "Game Won", JOptionPane.INFORMATION_MESSAGE);
                            gameEnded = true;
                            undoButton.setEnabled(false);
                        } else if (state == GameState.DRAW) {
                            JOptionPane.showMessageDialog(null, "Draw", "Game Draw", JOptionPane.INFORMATION_MESSAGE);
                            gameEnded = true;
                            undoButton.setEnabled(false);
                        }
                    }
                });
                buttonPanel.add(button);
            }
        }

        // Create a subpanel for the additional buttons
        JPanel subPanel = new JPanel();
        subPanel.add(undoButton);

        // Add the button grid and subpanel to the main panel
        add(buttonPanel, BorderLayout.CENTER);
        add(subPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the JFrame
        setVisible(true);
    }
}
