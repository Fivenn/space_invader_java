/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.View;

import projet.Controller.GameController;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class MainView extends JFrame implements KeyListener, Observer {
    private final GameController gameController; //Contrôleur de notre jeu
    private final PlaygroundAreaView playgroundAreaView; // Plateau de jeu
    private final InformationAreaView informationAreaView; // Zone d'information du jeu
    private final GameOverView gameOverView; // Vue Game Over

    /* classe définissant la fenêtre principale de jeu */
    public MainView(GameController gameController) throws HeadlessException {
        /* Initialisation des variables et objets et configuration de la fenêtre */
        this.gameController = gameController;
        this.gameController.addObserver(this);

        playgroundAreaView = new PlaygroundAreaView(this.gameController);
        informationAreaView = new InformationAreaView(this.gameController);
        gameOverView = new GameOverView();

        this.setLayout(new BorderLayout());
        this.getContentPane().add(playgroundAreaView, BorderLayout.WEST);
        this.getContentPane().add(informationAreaView, BorderLayout.EAST);

        setResizable(false);
        pack();

        setTitle("Piou - Piou");
        setSize(1150, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addKeyListener(this);
        setVisible(true);
        this.requestFocus(false);
    }

    /* Fonction permettant de changer la vue du plateau de jeu avec celle du game over (dans les deux sens) */
    public void changeView(){
        if(this.gameController.isGameIsOver()){
            this.getContentPane().removeAll();
            this.getContentPane().add(this.gameOverView, BorderLayout.WEST);
            this.getContentPane().add(informationAreaView, BorderLayout.EAST);
            this.gameOverView.repaint();
        }else{
            this.getContentPane().removeAll();
            this.getContentPane().add(this.playgroundAreaView,  BorderLayout.WEST);
            this.getContentPane().add(informationAreaView, BorderLayout.EAST);
        }      
        this.revalidate();
        this.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    /* Fonction permettant d'écouter les entrées clavier */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            /* Si on appuie sur la touche espace, on tire un missile */
            case KeyEvent.VK_SPACE:
                this.gameController.setSpaceTouch(true);
                break;
                /* Si on appuie sur la flèche gauche du clavier, on déplace le vaisseau à gauche */
            case KeyEvent.VK_LEFT:
                this.gameController.setArrowTouch(-1);
                break;
                /* Si on appuie sur la flèche droite du clavier, on déplace le vaisseau à droite */
            case KeyEvent.VK_RIGHT:
                this.gameController.setArrowTouch(1);
                break;
            default:
                break;
        }
        this.gameController.actionJoueur();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            /* Si on relâche sur la touche "P", alors on met le jeu en pause */
            case KeyEvent.VK_P:
                this.gameController.pauseGame();
                break;
                /* Si on relâche la touche espace, on arrête de tirer */
            case KeyEvent.VK_SPACE:
                this.gameController.setSpaceTouch(false);
                break;
                /* Si on relâche la flèche de gauche, on arrête de faire bouger le vaisseau */
            case KeyEvent.VK_LEFT:
                this.gameController.setArrowTouch(0);
                break;
                /* Si on relâche la flèche de gauche, on arrête de faire bouger le vaisseau */
            case KeyEvent.VK_RIGHT:
                this.gameController.setArrowTouch(0);
                break;
            default:
                break;
        }
    }

    @Override
    /* Fonction permettant d'alterner entre le plateau de jeu et le game over lorsque
    * l'on reçoit une notification de l'observable (GameController).
     */
    public void update(Observable o, Object arg) {
        if(this.getContentPane().getComponent(0) == this.playgroundAreaView && gameController.isGameIsOver()){
            this.changeView();
        }else if(this.getContentPane().getComponent(0) == this.gameOverView && !this.gameController.isGameIsOver()){
            this.changeView();
        }
    }
}
