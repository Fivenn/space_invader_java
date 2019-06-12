package projet.View;

import projet.Model.gameClass.*;

import javax.swing.*;
import java.awt.*;

public class PlaygroundArea extends JPanel {
    public PlaygroundArea() {
        setBackground(Color.BLACK);

        Alien alien = new Alien();
        AlienSpaceShip alienSpaceShip = new AlienSpaceShip();
        Building building = new Building();
        Bullet bullet = new Bullet();
        SpaceShip spaceShip = new SpaceShip();


    }
}