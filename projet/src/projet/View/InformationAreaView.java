package projet.View;

import javax.swing.*;
import java.awt.*;

public class InformationAreaView extends JPanel {
    public InformationAreaView() {

        setBackground(Color.BLUE);
        this.setPreferredSize(new Dimension(250, 1000));

        this.setLayout(new GridLayout(4, 1));

        JButton newGameButton = new JButton("New Game");
        JButton pauseButton = new JButton("Pause");
        JButton quitButton = new JButton("Quit");
        JButton helpButton = new JButton("Help");

        this.add(newGameButton);
        this.add(pauseButton);
        this.add(quitButton);
        this.add(helpButton);


    }
}
