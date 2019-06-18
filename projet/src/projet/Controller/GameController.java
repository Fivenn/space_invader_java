package projet.Controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import projet.Model.gameClass.*;

public class GameController extends Observable implements ActionListener{

    /**
     * @return the aliens
     */
    public List<List<Alien>> getAliens() {
        return aliens;
    }

    /**
     * @param aliens the aliens to set
     */
    public void setAliens(List<List<Alien>> aliens) {
        this.aliens = aliens;
    }
    private Player player;
    private SpaceShip spaceShip;
    private List<Building> buildings;
    private List<List<Alien>> aliens;
    private AlienSpaceShip alienSpaceShip;
    
    private int isAliensOnTheWall = 1;

    public GameController() {
        this.player = new Player(0, 3, "BestPlayer");
        this.spaceShip = new SpaceShip(400.0, 600.0, 10, new ImageIcon(this.getClass().getClassLoader().getResource("ship.gif")));
        this.alienSpaceShip = new AlienSpaceShip(0,0,2,300, new ImageIcon(this.getClass().getClassLoader().getResource("alien.gif")));
        this.aliens = new ArrayList();
        buildAliensList();
        //TMP
        //aliens.add(new Alien(10, 10, 5, 5, new ImageIcon(this.getClass().getClassLoader().getResource("alien.gif"))));
    
        Timer timer = new Timer(50, this);
        timer.start();
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
    
    private void buildAliensList(){
        List<Alien> listAliens;
        ImageIcon alienIcon = new ImageIcon(this.getClass().getClassLoader().getResource("alien.gif"));
        
        for(int y = 2;y<7;y++){
            listAliens = new ArrayList<>();
            for(int x = 1;x<11;x++){
                listAliens.add(new Alien(x*50, y*50, 20, 10, alienIcon));
            }
            this.aliens.add(listAliens);
        }
    }
    
    private void moveAliens(){
        boolean shouldMoveDown = false;
        if(this.aliens.get(0).get(9).getX()>856){
            this.isAliensOnTheWall = -1;
            shouldMoveDown = true;
        }else if(this.aliens.get(0).get(0).getX()<1){
            this.isAliensOnTheWall = 1;
            shouldMoveDown = true;
        }
        
        this.aliens.forEach((ls) -> {
            ls.forEach((a) -> {
                a.setX(a.getX() + a.getSpeed()*isAliensOnTheWall);
            });
        });
        
        if(shouldMoveDown){
            this.aliens.forEach((ls) -> {
                ls.forEach((a) -> {
                    a.setY(a.getY() + 50);
                    if(a.getY()>600){
                        this.player.setLifePoints(this.player.getLifePoints()-1);
                        System.out.println(this.player.getLifePoints());
                    }
                });
            });
            
        }
        

    }
         
    @Override
    public void actionPerformed(ActionEvent e) {
        moveAliens();
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



}
