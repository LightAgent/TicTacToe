package view;

import javax.swing.*;

import controller.Controller;
import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame{
    private static final int SQUARE_DIMENSION = 150;
    public Controller controller;
    private XOButton[][] xoButtons;
    private boolean gameEnded;

    public GameWindow(Controller controller) {
        this.controller = controller;
        this.xoButtons = new XOButton[3][3];
        initializeWindow();
    }
    private void redrawBoard(XOBoard board){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ImageIcon imageIcon = FlyweightFactory.createImage(board.getBoard()[i][j].getMarkerType());
                xoButtons[i][j].setIcon(imageIcon);
            }
        }
    }
    private void initializeWindow() {
        setTitle("Grid of Buttons");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        // Create buttons and add ActionListener
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                XOButton button = new XOButton(i,j);
                ImageIcon imageIcon = new ImageIcon("images\\default.png");
                Image img = imageIcon.getImage().getScaledInstance(SQUARE_DIMENSION, SQUARE_DIMENSION, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(img);
                button.setIcon(scaledIcon);
                button.setPreferredSize(new Dimension(150, 150));
                button.setBackground(new Color(239,224,187));
                xoButtons[i][j] = button;
                
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        if(gameEnded) return;
                        XOButton clickedButton = (XOButton) event.getSource();
                        XOButton button = xoButtons[clickedButton.getPosition().getX()][clickedButton.getPosition().getY()];
                        if(!controller.getMarker(button.getPosition()).getMarkerType().equals(MarkerType.EMPTY)) return;
                        // 
                        // button.setIcon(imageIcon);
                        GameState state = controller.play(clickedButton.getPosition());
                        redrawBoard(controller.getBoard());
                        if(state==GameState.WON){
                            JOptionPane.showMessageDialog(null, controller.getCurrentTurn() == MarkerType.X ? "X Won": "O Won", "Meow", JOptionPane.INFORMATION_MESSAGE);
                            gameEnded = true;
                        }else if(state==GameState.DRAW){
                            JOptionPane.showMessageDialog(null, "Draw", "Meow", JOptionPane.INFORMATION_MESSAGE);
                            gameEnded = true;
                        }
                    }
                });
                add(button);
            }
        }

        pack();
        setLocationRelativeTo(null); // Center the JFrame
    }
}
