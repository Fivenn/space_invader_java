package projet.View;

import projet.Controller.GameController;

import javax.swing.*;
import java.awt.*;
import static java.awt.BorderLayout.CENTER;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

public class InformationAreaView extends JPanel {
    private GameController gameController;
    private final HelpView helpView;
    private int pause = 0;

    public InformationAreaView(GameController gameController) {
        this.gameController = gameController;
        helpView = new HelpView(this.gameController,this);
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(230, 900));

        this.setLayout(new BorderLayout());
        
        JPanel panelHelpAndQuit = new JPanel(new GridLayout(1,4));
        panelHelpAndQuit.setBackground(Color.black);
        JButton newGameButton = new JButton("New Game");
        newGameButton.setPreferredSize(new Dimension(225, 100));
        JButton pauseButton = new JButton("II");
        
        
        JButton quitButton = new JButton("X");
        quitButton.setPreferredSize(new Dimension(50,50));
        JButton helpButton = new JButton("?");
        helpButton.setSize(50,50);
        
        newGameButton.setBackground(Color.black);
        newGameButton.setForeground(Color.white);
        newGameButton.setOpaque(true);
        newGameButton.setBorderPainted(false);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.resetGameControllerWhenNewGame();
            }
        });

        pauseButton.setBackground(Color.black);
        pauseButton.setForeground(Color.white);
        pauseButton.setOpaque(true);
        pauseButton.setBorderPainted(false);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.pauseGame();
            }
        });
        
        helpButton.setBackground(Color.black);
        helpButton.setForeground(Color.white);
        helpButton.setOpaque(true);
        helpButton.setBorderPainted(false);
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!gameController.isPause()) {
                    gameController.pauseGame();
                }
                // Afficher le nouveau Panel
                helpView.setVisible(true);
            }
        });

        quitButton.setBackground(Color.black);
        quitButton.setForeground(Color.red);
        quitButton.setOpaque(true);
        quitButton.setBorderPainted(false);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        panelHelpAndQuit.add(pauseButton);
        panelHelpAndQuit.add(new JLabel());
        panelHelpAndQuit.add(helpButton);
        panelHelpAndQuit.add(quitButton);
        
        pauseButton.setFocusable(false);
        helpButton.setFocusable(false);
        newGameButton.setFocusable(false);
        
        this.add(panelHelpAndQuit,NORTH);
        this.add(new LeaderBoardView(this.gameController),CENTER);
        this.add(newGameButton,SOUTH);
        
        
    }
}
