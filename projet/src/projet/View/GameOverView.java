package projet.View;

import javax.swing.*;
import java.awt.*;

public class GameOverView extends JPanel {

    /* Variable contenant l'image du Game Over */
    ImageIcon go = new ImageIcon(this.getClass().getClassLoader().getResource("gameOver.gif"));

    /* La vue représentant le Game Over du jeu */
    public GameOverView() {
        /* Configuration de la fenêtre */
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(900, 1000));
        this.setLayout(new BorderLayout());

        /* Création d'un label contenant l'image du Game Over */
        JLabel gameOverLabel = new JLabel(this.go);

        /* Ajout de l'image du Game Over dans le label */
        this.add(gameOverLabel, BorderLayout.CENTER);
    }

    
    
}
