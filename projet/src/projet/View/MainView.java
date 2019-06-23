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

/**
 *
 * @author mallou
 */
public class MainView extends JFrame implements KeyListener, Observer {
    private final GameController gameController;
    private final PlaygroundAreaView playgroundAreaView;
    private final InformationAreaView informationAreaView;
    private final GameOverView gameOverView;

    public MainView(GameController gameController) throws HeadlessException {
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
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                this.gameController.setSpaceTouch(true);
                break;
            case KeyEvent.VK_LEFT:
                this.gameController.setArrowTouch(-1);
                break;
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
            case KeyEvent.VK_P:
                this.gameController.pauseGame();
                break;
            case KeyEvent.VK_SPACE:
                this.gameController.setSpaceTouch(false);
                break;
            case KeyEvent.VK_LEFT:
                this.gameController.setArrowTouch(0);
                break;
            case KeyEvent.VK_RIGHT:
                this.gameController.setArrowTouch(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(this.getContentPane().getComponent(0) == this.playgroundAreaView && gameController.isGameIsOver()){
            this.changeView();
        }else if(this.getContentPane().getComponent(0) == this.gameOverView && !this.gameController.isGameIsOver()){
            this.changeView();
        }
    }
}
