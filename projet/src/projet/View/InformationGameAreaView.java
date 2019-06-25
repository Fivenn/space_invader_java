package projet.View;

import projet.Controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class InformationGameAreaView extends JPanel implements Observer {
    private GameController gameController; // Contrôleur du jeu
    private int points; // Nombre de points gagné par le joueur
    private int lifePoints; // Points de vie du joueur
    private String pseudo; // Pseudo du joueur
    private JLabel lifeLabel; // Label représentant la vie du joueur
    private JLabel scoreLabel; // Label représentant le score du joueur
    private JLabel pseudoPlayerLabel;

    /* Classe définissant la vue d'information sur la partie (présent au dessus du plateau de jeu) */
    public InformationGameAreaView(GameController gameController) {
        /* Initialisation des variables et configuration de la vue */
        this.gameController = gameController;
        this.setBorder(BorderFactory.createEmptyBorder(0, 7,0, 0));
        points = gameController.getPlayer().getPoints();
        lifePoints = gameController.getPlayer().getLifePoints();
        pseudo = gameController.getPlayer().getPseudo();

        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(900, 50));
        this.setLayout(new GridLayout(1,3));
        this.gameController.addObserver(this);

        scoreLabel = new JLabel("Score: "+points);
        lifeLabel = new JLabel("Life: "+lifePoints);
        pseudoPlayerLabel = new JLabel(pseudo);

        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(scoreLabel.getFont().deriveFont(30f));

        lifeLabel.setForeground(Color.WHITE);
        lifeLabel.setFont(lifeLabel.getFont().deriveFont(30f));

        pseudoPlayerLabel.setForeground(Color.white);
        pseudoPlayerLabel.setFont(pseudoPlayerLabel.getFont().deriveFont(30f));

        this.add(scoreLabel);
        this.add(lifeLabel);
        this.add(pseudoPlayerLabel);
    }

    /* Fonction permettant d'actualiser l'affichage des points de vie du joueur */
    public void updateLifePoints() {
        lifePoints = this.gameController.getPlayer().getLifePoints();
        lifeLabel.setText("Life: "+this.lifePoints);
    }

    /* Fonction permettant d'actualiser le score du joueur */
    public void updateScore() {
        points = this.gameController.getPlayer().getPoints();
        scoreLabel.setText("Score: "+this.points);
    }

    public void updatePseudo() {
        pseudo = this.gameController.getPlayer().getPseudo();
        pseudoPlayerLabel.setText(this.pseudo);
    }

    @Override
    /* Fonction permettant d'actualiser les points de vie et le score d'un joueur quand on reçoit une
    * notification de l'observable (GameController).
     */
    public void update(Observable o, Object arg) {
        updateLifePoints();
        updateScore();
        updatePseudo();
    }
}
