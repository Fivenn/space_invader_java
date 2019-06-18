/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.View;

import projet.Controller.GameController;
import projet.Model.gameClass.Alien;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author mallou
 */
public class MainView extends JFrame implements KeyListener{
    GameController gameController;

    public MainView(GameController gameController) throws HeadlessException {
        this.gameController = gameController;

        InformationAreaView informationAreaView = new InformationAreaView(this.gameController);
        PlaygroundAreaView playgroundAreaView = new PlaygroundAreaView(this.gameController);

        this.setLayout(new BorderLayout());
        this.add(playgroundAreaView, BorderLayout.WEST);
        this.add(informationAreaView, BorderLayout.EAST);

        setResizable(false);
        pack();

        setTitle("Space Invaders");
        setSize(1150, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addKeyListener(this);
        setVisible(true);
        this.requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("projet.View.MainView.keyTyped()");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.gameController.actionJoueur(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
}
