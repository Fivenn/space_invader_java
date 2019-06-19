package projet.View;

import javax.swing.*;
import java.awt.*;

public class HelpView extends JPanel {
    public HelpView() {
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(900, 1000));
        this.setLayout(new BorderLayout());

        JLabel helpLabel = new JLabel("qdfhjqsdgflqsdfgq;jsdgfkqjhsd");
        JButton backButton = new JButton("Back");
        helpLabel.setForeground(Color.WHITE);

        this.add(helpLabel, BorderLayout.CENTER);
        this.add(backButton, BorderLayout.SOUTH);
    }
}
