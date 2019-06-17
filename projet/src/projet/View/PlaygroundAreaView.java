package projet.View;

import projet.Controller.GameController;
import projet.Model.gameClass.*;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class PlaygroundAreaView extends JPanel implements Observer {
    private GameController gameController;
    public PlaygroundAreaView(GameController gameController) {
        this.gameController = gameController;
        gameController.addObserver(this);
        setBackground(Color.BLACK);

        InformationGameAreaView informationGameAreaView = new InformationGameAreaView();

        this.setPreferredSize(new Dimension(900, 1000));
        this.setLayout(new BorderLayout());

        this.add(informationGameAreaView, BorderLayout.NORTH);

        //Init tous les objets visuels
    }


    @Override
    public void update(Observable o, Object arg) {

    }
}