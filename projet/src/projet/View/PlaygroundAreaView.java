package projet.View;

import projet.Model.gameClass.*;

import javax.swing.*;
import java.awt.*;

public class PlaygroundAreaView extends JPanel {
    public PlaygroundAreaView() {
        setBackground(Color.BLACK);

        InformationGameAreaView informationGameAreaView = new InformationGameAreaView();

        this.setPreferredSize(new Dimension(900, 1000));
        this.setLayout(new BorderLayout());

        this.add(informationGameAreaView, BorderLayout.NORTH);

        //Init tous les objets visuels
    }
}