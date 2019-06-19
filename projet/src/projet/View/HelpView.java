package projet.View;

import projet.Controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpView extends JPanel {
    private GameController gameController;

    public HelpView(GameController gameController) {
        this.gameController = gameController;
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(900, 1000));
        this.setLayout(new BorderLayout());

        JLabel helpLabel = new JLabel("qdfhjqsdgflqsdfgq;jsdgfkqjhsd");
        JButton backButton = new JButton("Back");

        helpLabel.setForeground(Color.WHITE);

        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.pauseGame();
            }
        });

        this.add(helpLabel, BorderLayout.CENTER);
        this.add(backButton, BorderLayout.SOUTH);
    }
}
