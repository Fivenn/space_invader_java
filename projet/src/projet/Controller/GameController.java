package projet.Controller;


import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Observable;
import javax.swing.ImageIcon;

import projet.Model.gameClass.*;

public class GameController extends Observable{
    private Player player;
    private SpaceShip spaceShip;
    private List<Building> buildings;
    private List<Alien> aliens;
    private AlienSpaceShip alienSpaceShip;
    private Bullet bullet;
    
    public GameController() {
        this.player = new Player(0, 3, "Coucou");
        this.spaceShip = new SpaceShip(0, 0, 1, new ImageIcon(this.getClass().getClassLoader().getResource("ship.gif")));
        this.alienSpaceShip = new AlienSpaceShip(0,0,2,300, new ImageIcon(this.getClass().getClassLoader().getResource("alien.gif")));
        this.bullet = new Bullet(0, 0, 1, new ImageIcon(this.getClass().getClassLoader().getResource("shot.gif")));
        this.spaceShip = new SpaceShip(400.0, 600.0, 10, new ImageIcon(this.getClass().getClassLoader().getResource("ship.gif")));
        this.alienSpaceShip = new AlienSpaceShip(0,0,2,300, new ImageIcon(this.getClass().getClassLoader().getResource("alien.gif")));
    }

    public void actionJoueur(int keyCode){
        switch(keyCode){
            case KeyEvent.VK_LEFT:
                getSpaceShip().move(true);
                break;
            case KeyEvent.VK_RIGHT :
                getSpaceShip().move(false);
                break;
            case KeyEvent.VK_SPACE :
                getSpaceShip().shoot();
                break;
            default:
                break;
        }
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the spaceShip
     */
    public SpaceShip getSpaceShip() {
        return spaceShip;
    }

    /**
     * @param spaceShip the spaceShip to set
     */
    public void setSpaceShip(SpaceShip spaceShip) {
        this.spaceShip = spaceShip;
    }

    /**
     * @return the Buildings
     */
    public List<Building> getBuildings() {
        return buildings;
    }

    /**
     * @param Buildings the Buildings to set
     */
    public void setBuildings(List<Building> Buildings) {
        this.buildings = Buildings;
    }

    /**
     * @return the Aliens
     */
    public List<Alien> getAliens() {
        return aliens;
    }

    /**
     * @param Aliens the Aliens to set
     */
    public void setAliens(List<Alien> Aliens) {
        this.aliens = Aliens;
    }

    /**
     * @return the alienSpaceShip
     */
    public AlienSpaceShip getAlienSpaceShip() {
        return alienSpaceShip;
    }

    /**
     * @param alienSpaceShip the alienSpaceShip to set
     */
    public void setAlienSpaceShip(AlienSpaceShip alienSpaceShip) {
        this.alienSpaceShip = alienSpaceShip;
    }

    public Bullet getBullet() {
        return bullet;
    }
}
