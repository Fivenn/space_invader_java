package projet.View;

import projet.Controller.GameController;

import javax.swing.*;
import java.awt.*;

public class InformationGameAreaView extends JPanel {
    private GameController gameController;
    private int points;
    private int lifePoints;
    private String pseudo;

    public InformationGameAreaView(GameController gameController) {
        this.gameController = gameController;
        points = gameController.getPlayer().getPoints();
        lifePoints = gameController.getPlayer().getLifePoints();
        pseudo = gameController.getPlayer().getPseudo();

        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(900, 50));
        this.setLayout(new GridLayout(1,3));

        JLabel scoreLabel = new JLabel("Score: ");
        JLabel lifeLabel = new JLabel("Life: ");
        JLabel highScoreLabel = new JLabel("High Score: ");

        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(scoreLabel.getFont().deriveFont(30f));

        lifeLabel.setForeground(Color.WHITE);
        lifeLabel.setFont(lifeLabel.getFont().deriveFont(30f));

        highScoreLabel.setForeground(Color.white);
        highScoreLabel.setFont(highScoreLabel.getFont().deriveFont(30f));

        this.add(scoreLabel);
        this.add(lifeLabel);
        this.add(highScoreLabel);

    }
}