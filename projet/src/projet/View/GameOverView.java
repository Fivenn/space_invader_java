package projet.View;

import projet.Controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameOverView extends JPanel {

    public GameOverView() {
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(900, 1000));
        this.setLayout(new BorderLayout());

        JLabel gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setBackground(Color.BLACK);
        gameOverLabel.setForeground(Color.white);
        gameOverLabel.setFont(gameOverLabel.getFont().deriveFont(64f));

        this.add(gameOverLabel, BorderLayout.CENTER);
    }
}
