package projet.Controller;


import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
// import projet.Model.gameClass.Player;
import projet.Model.gameClass.SpaceShip;
import projet.View.PlaygroundAreaView;

public class GameController extends Observable{
    private SpaceShip spaceShip;
    // private Player player;
    
    public GameController() {
        this.spaceShip = new SpaceShip(0, 0, 10, new ImageIcon("src/projet/Ressources/ship.gif"));
    }

    
    
    

    
}
