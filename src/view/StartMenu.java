package view;

import javax.swing.*;

import controller.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//* Singelton
public class StartMenu extends JFrame {

    private static StartMenu instance;

    private StartMenu() {
        initializeUI();
    }
    public static synchronized StartMenu getInstance(){
        if(instance==null){
            instance = new StartMenu();
        }
        return instance;
    }

    private void initializeUI() {
        setTitle("Tic Tac Toe Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        JButton singlePlayerButton = new JButton("Single Player");
        JButton twoPlayerButton = new JButton("Two Player");

        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    new GameWindow(new SinglePlayerController()).setVisible(true);
                });
            }
        });

        twoPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    new GameWindow(new TwoPlayerController()).setVisible(true);
                });
            }
        });
        singlePlayerButton.setPreferredSize(new Dimension(550,300));
        singlePlayerButton.setPreferredSize(new Dimension(550,300));

        add(singlePlayerButton);
        add(twoPlayerButton);

        pack();
        setLocationRelativeTo(null);
    }
    
    public void showWindow(){
        setVisible(true);
    }

}
