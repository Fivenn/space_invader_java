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

    public MainView(GameController gameController) throws HeadlessException {
        this.gameController = gameController;
        this.gameController.addObserver(this);

        playgroundAreaView = new PlaygroundAreaView(this.gameController);
        informationAreaView = new InformationAreaView(this.gameController);
        helpView = new HelpView(this.gameController);

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
        if(KeyEvent.VK_P == e.getKeyCode()){
                this.gameController.pauseGame();
                this.gameController.setArrowTouch(0);
                this.gameController.setSpaceTouch(false);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getExtendedKeyCode() + "              + "+ KeyEvent.VK_SPACE);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                this.gameController.setSpaceTouch(true);
                System.out.println(this.gameController.getArrowTouch() + "  " + this.gameController.isSpaceTouch());
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
        System.out.println(this.gameController.getArrowTouch() + "  " + this.gameController.isSpaceTouch());
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
        if(gameController.isGameIsOver()){
            this.changeView1Toview2(this.playgroundAreaView, this.helpView);
        }
    }
}
