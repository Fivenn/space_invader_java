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

    private Player player;
    private SpaceShip spaceShip;
    private List<Building> buildings;
    private List<List<Alien>> aliens;
    private AlienSpaceShip alienSpaceShip;
    
    private Timer timer;
    private int isAliensOnTheWall = 1;
    private boolean pause = false;

    public GameController() {
        this.player = new Player(0, 3, "BestPlayer");
        this.spaceShip = new SpaceShip(400.0, 600.0, 10, new ImageIcon(this.getClass().getClassLoader().getResource("ship.gif")));
        this.alienSpaceShip = new AlienSpaceShip(0,0,2,300, new ImageIcon(this.getClass().getClassLoader().getResource("alien.gif")));
        this.aliens = new ArrayList();
        buildAliensList();
        
        int i = 0;
        for(List<Alien> l : aliens){
            for(Alien a : l){
                System.out.println("Liste "+i+" x : "+a.getX()+ " y :"+ a.getY());
            }
            i++;
        }
            
        this.timer = new Timer(100, this);
        this.timer.start();
    }

    public void pauseGame() {
        if (this.pause) {
            this.timer.start();
            this.pause = false;
        } else {
            this.timer.stop();
            this.pause = true;
        }
    }

    public void resetGameController() {
        this.isAliensOnTheWall = 1;
        this.pause = false;
        this.aliens.clear();

        this.player = new Player(0, 3, "BestPlayer");
        this.spaceShip = new SpaceShip(400.0, 600.0, 10, new ImageIcon(this.getClass().getClassLoader().getResource("ship.gif")));
        this.alienSpaceShip = new AlienSpaceShip(0,0,2,300, new ImageIcon(this.getClass().getClassLoader().getResource("alien.gif")));
        buildAliensList();
    }

    /**
     * @param aliens the aliens to set
     */
    public void setAliens(List<List<Alien>> aliens) {
        this.aliens = aliens;
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
        
        for(int x = 1;x<9;x++){
            listAliens = new ArrayList<>();
            for(int y = 2;y<7;y++){
                listAliens.add(new Alien(x*60, y*50, 1, 10, alienIcon));
            }
            this.aliens.add(listAliens);
        }
    }
    
    private void moveAliens(){
        boolean shouldMoveDown = false;
        if(this.aliens.get(this.aliens.size() - 1).get(0).getX()>856){
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
                        if(this.player.getLifePoints()<0){
                            this.timer.stop();
                            
                        }
                        System.out.println(this.player.getLifePoints());
                    }
                });
            });
            
        }
    }
         
    @Override
    public void actionPerformed(ActionEvent e) {
        moveAliens();
        if(this.getSpaceShip().getBullet() != null){
            this.checkCollision(this.getSpaceShip().getBullet());
            this.getSpaceShip().getBullet().move(true);
            
        }
        this.setChanged();
        this.notifyObservers();
    }
    
    
    public void checkCollision(DynamicGameObject dgo){
        int i = 0;
        boolean stop = false;
        List<Alien> l;
        Alien al;
        int alx ;
        int aly;
        int x = (int) dgo.getX();
        int y = (int) dgo.getY();
        while(i<aliens.size()-1 && !stop){
            
            l = this.aliens.get(i);
            al = l.get(l.size()-1);
            alx = (int) al.getX(); 
            aly = (int) al.getY(); 

            if(alx <= x && alx + al.getWidth()>= x){
                System.out.println("i : "+i+" "+" al.getY() : " +al.getY() + "dgo.getY  : "+ dgo.getY() +(al.getY() + al.getHeigth()<= dgo.getY()));

                if(aly <= y && aly + al.getHeigth()>= y){
                    stop = true;
                    System.out.println(" i : "+i+" x :"+dgo.getX()+ " y : "+dgo.getY());
                    this.timer.stop();
                
                }
            }
            i+=1;
        }
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

    public boolean isPause() {
        return pause;
    }
}
