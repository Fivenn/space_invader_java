package projet.Controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
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
    
    private final Timer timer;
    private int isAliensOnTheWall = 1;
    private int isAlienSpaceShipOnTheWall = 1;
    private boolean pause = false;
    private boolean isGameIsOver = false;
    
    private int nbAliensLigne = 2;
    private int nbAliensColonnes = 2;
    private final int nbBuilding = 4;
    private int nbChancesBulletAlien = 5000;
    private int nbChancesSpawnVaisseau = 10;
    private int niveau;
    
    public GameController() {
        this.niveau = 1;
        this.aliens = new ArrayList();
        this.player = new Player(0, 3, "BestPlayer");
        initGameControllerObjects();
        this.timer = new Timer(5, this);
        this.timer.start();
    }

    private void initGameControllerObjects() {
        this.spaceShip = new SpaceShip(400.0, 580.0, 10, new ImageIcon(this.getClass().getClassLoader().getResource("spaceshipPiou.png")));
        this.buildings = new ArrayList<>();
        buildAliensList();
        buildBuildingList();
    }

    public void pauseGame() {
        if (this.pause) {
            this.timer.start();
        } else {
            this.timer.stop();
        }
        this.pause = !pause;
    }


    public void resetGameController() {
        this.isAliensOnTheWall = 1;
        this.pause = false;
        this.aliens .clear();
        initGameControllerObjects();
        this.timer.restart();
    }

    public void resetGameControllerWhenNewGame() {
        this.isAliensOnTheWall = 1;
        this.pause = false;
        this.aliens .clear();
        this.player = new Player(0, 3, "BestPlayer");
        initGameControllerObjects();
        this.timer.restart();
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
        ImageIcon alienIcon = new ImageIcon(this.getClass().getClassLoader().getResource("fox.png"));
        
        for(int x = 1;x<nbAliensLigne+1;x++){
            listAliens = new ArrayList<>();
            for(int y = 2;y<nbAliensColonnes+2;y++){
                listAliens.add(new Alien(x*60, y*50, 1*(niveau/5)+1, 10, alienIcon));
            }   
            this.aliens.add(listAliens);
        }
    }
    
    public void changementDeNiveau(){
        Random rand = new Random();
        this.niveau +=1;
        this.nbAliensColonnes = (rand.nextInt()%this.niveau)%10 + 2;
        this.nbAliensLigne = (rand.nextInt()%this.niveau)%10 + 2;
        this.nbChancesBulletAlien = (rand.nextInt()%this.niveau)%3000 - niveau*100;
    }
    
    private void buildBuildingList(){
        if(getNbBuilding() !=0){
            double x = 800 / (getNbBuilding()) - 29.5;
            for(int i = 1; i<getNbBuilding()+1;i++){
                buildings .add(new Building(x*i, 500, 6,new ImageIcon(this.getClass().getClassLoader().getResource("poulailler.png"))));
            } 
        }
        
    }

    private void moveAliens(){
        boolean shouldMoveDown = false;
        Random rand = new Random();
        
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
                    if(rand.nextInt()%nbChancesBulletAlien == 0){
                        a.shoot();
                    }

                    if(a.getBullet()!= null){
                        a.getBullet().move(false);
                    }
                });
            });
        
        if(shouldMoveDown){
            for(int j = 0;j<aliens.size();j++){
                for(int i = 0;i<aliens.get(j).size();i++){
                    aliens.get(j).get(i).setY(aliens.get(j).get(i).getY() + 50);
                    if(aliens.get(j).get(i).getY()>550){
                        this.isGameIsOver = true;
                        System.out.println(this.isGameIsOver);
                        break;
                    }
                }
            }
            
        }
        
        if(getNbChancesSpawnVaisseau() != 0 && rand.nextInt()%getNbChancesSpawnVaisseau() == 0){
            this.setAlienSpaceShip(new AlienSpaceShip(50, 10, 10, 100, new ImageIcon(this.getClass().getClassLoader().getResource("spaceShipAlien.png"))));
            setNbChancesSpawnVaisseau(0);
        }else if(getNbChancesSpawnVaisseau()==0){
            shouldMoveDown = false;
            if(this.getAlienSpaceShip().getX()>856){
                shouldMoveDown = true;
                this.isAlienSpaceShipOnTheWall = -1;
            }else if(this.getAlienSpaceShip().getX()<1){
                shouldMoveDown = true;
                this.isAlienSpaceShipOnTheWall = 1;
            } 
            
            this.getAlienSpaceShip().setX(this.getAlienSpaceShip().getX() + this.getAlienSpaceShip().getSpeed()*isAlienSpaceShipOnTheWall);
            if(shouldMoveDown){
                this.getAlienSpaceShip().setY(this.getAlienSpaceShip().getY() + 50);
                
                if(this.getAlienSpaceShip().getY()>550){
                    
                }
            }
        }
    }
         
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!this.aliens.isEmpty() && !this.aliens.get(this.aliens.size() - 1).isEmpty()){
            moveAliens();
            if(this.getSpaceShip().getBullet() != null){
                this.getSpaceShip().getBullet().move(true);  

            }
            this.setChanged();
            this.notifyObservers();
        }else{
            changementDeNiveau();
            resetGameController();
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

    /**
     * @return the nbBuilding
     */
    public int getNbBuilding() {
        return nbBuilding;
    }

    public boolean isGameIsOver() {
        return isGameIsOver;
    }

    /**
     * @return the nbChancesSpawnVaisseau
     */
    public int getNbChancesSpawnVaisseau() {
        return nbChancesSpawnVaisseau;
    }

    /**
     * @param nbChancesSpawnVaisseau the nbChancesSpawnVaisseau to set
     */
    public void setNbChancesSpawnVaisseau(int nbChancesSpawnVaisseau) {
        this.nbChancesSpawnVaisseau = nbChancesSpawnVaisseau;
    }

    public void setGameIsOver(boolean gameIsOver) {
        isGameIsOver = gameIsOver;
    }
}
