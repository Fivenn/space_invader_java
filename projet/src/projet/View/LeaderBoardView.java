/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.View;

import projet.Controller.GameController;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mallou
 */
public class LeaderBoardView extends JPanel{
    private GameController gameController;

    public LeaderBoardView(GameController gameController) {
        this.gameController = gameController;
        this.setPreferredSize(new Dimension(235, 600));
        this.setLayout(new BorderLayout());
        this.setOpaque(true);
        JPanel titreTableau = new JPanel(new GridLayout(1,2));

        //titreTableau.add(new JLabel("Pseudo"));
        //titreTableau.add(new JLabel("Score"));
        this.add(titreTableau,CENTER);
        this.add(construireTableau(),BorderLayout.SOUTH);
    }
    private JPanel construireTableau(){
        JPanel tableau = new JPanel();
        int nbRows = 10; //A remplacer par nombre de meilleurs score voulus;
        tableau = new JPanel(new GridLayout(nbRows,2));
        return tableau;
    }
     
}
