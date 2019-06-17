/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.View;

import projet.Controller.GameController;
import projet.Model.gameClass.Alien;

import java.awt.*;
import javax.swing.JFrame;

/**
 *
 * @author mallou
 */
public class MainView extends JFrame{

    public MainView() throws HeadlessException {
        GameController gameController = new GameController();

        InformationAreaView informationAreaView = new InformationAreaView();
        PlaygroundAreaView playgroundAreaView = new PlaygroundAreaView(gameController);

        this.setLayout(new BorderLayout());
        this.add(playgroundAreaView, BorderLayout.WEST);
        this.add(informationAreaView, BorderLayout.EAST);

        setResizable(true);
        pack();

        setTitle("Space Invaders");
        setSize(1150, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }
}
