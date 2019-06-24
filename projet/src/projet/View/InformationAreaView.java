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
    private final GameController gameController; //Contrôleur de notre jeu
    private final HelpView helpView; // Vue présentant la zone d'aide et de configuration

    /* Classe définissant la vue d'information visible à droite du plateau de jeu */
    public InformationAreaView(GameController gameController) {
        /* initialisation des variables utiles à la vue */
        this.gameController = gameController;
        helpView = new HelpView(this.gameController,this);

        /* Configuration de la vue */
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
        /* Déclaration d'un écouteur sur le bouton New Game */
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
        /* Déclaration d'un écouteur sur le bouton Pause */
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
        /* Déclaration d'un écouteur sur le bouton d'aide */
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* Si le jeu n'est pas en pause on le met en pause */
                if(!gameController.isPause()) {
                    gameController.pauseGame();
                }
                /* Afficher le nouveau Panel */
                helpView.setVisible(true);
            }
        });

        quitButton.setBackground(Color.black);
        quitButton.setForeground(Color.red);
        quitButton.setOpaque(true);
        quitButton.setBorderPainted(false);
        /* Déclaration d'un écouteur sur le bouton Quitter */
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Stop l'application avec un code de retour 0
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
