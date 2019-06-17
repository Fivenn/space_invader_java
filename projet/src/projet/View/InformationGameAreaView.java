package projet.View;

import javax.swing.*;
import java.awt.*;

public class InformationGameAreaView extends JPanel {
    public InformationGameAreaView() {
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(900, 50));

        JLabel scoreLabel = new JLabel("Score: ");
        JLabel lifeLabel = new JLabel("Life: ");
        JLabel highScoreLabel = new JLabel("High Score: ");

        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setSize(300, 40);
        lifeLabel.setForeground(Color.WHITE);
        highScoreLabel.setForeground(Color.white);

        this.add(scoreLabel);
        this.add(lifeLabel);
        this.add(highScoreLabel);

    }
}
