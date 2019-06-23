package projet.View;

import projet.Controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameOverView extends JPanel {
    ImageIcon go = new ImageIcon(this.getClass().getClassLoader().getResource("gameOver.gif"));
    public GameOverView() {
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(900, 1000));
        this.setLayout(new BorderLayout());

        JLabel gameOverLabel = new JLabel(this.go);
        this.add(gameOverLabel, BorderLayout.CENTER);
    }

    
    
}
