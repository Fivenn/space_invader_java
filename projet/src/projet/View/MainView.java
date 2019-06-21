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
    private GameController gameController;
    private PlaygroundAreaView playgroundAreaView;
    private InformationAreaView informationAreaView;
    private HelpView helpView;
    private GameOverView gameOverView;

    public MainView(GameController gameController) throws HeadlessException {
        this.gameController = gameController;
        this.gameController.addObserver(this);

        playgroundAreaView = new PlaygroundAreaView(this.gameController);
        informationAreaView = new InformationAreaView(this.gameController);
        helpView = new HelpView(this.gameController);
        gameOverView = new GameOverView();

        this.setLayout(new BorderLayout());
        this.add(playgroundAreaView, BorderLayout.WEST);
        this.add(informationAreaView, BorderLayout.EAST);

        setResizable(false);
        pack();

        setTitle("Space Invaders");
        setSize(1150, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addKeyListener(this);
        setVisible(true);
        this.requestFocus();
    }

    public void changeView1Toview2(JPanel view1, JPanel view2) {
        getContentPane().remove(view1);
        getContentPane().add(view2);
        validate();
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!gameController.isGameIsOver()){
            this.gameController.actionJoueur(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("cc "+gameController.isGameIsOver());
        if(gameController.isGameIsOver()){
            this.changeView1Toview2(this.playgroundAreaView, this.gameOverView);
        }
        if(!gameController.isGameIsOver()){
            this.changeView1Toview2(this.gameOverView, this.playgroundAreaView);
        }
    }
}
