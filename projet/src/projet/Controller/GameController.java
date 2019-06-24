package projet.Controller;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import projet.Model.gameClass.*;

public class GameController extends Observable implements ActionListener{


    private boolean spaceTouch = false;
    private int arrowTouch = 0;

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
    private int nbChancesBulletAlien = 2;
    private int nbChancesSpawnVaisseau = 10000;
    private int alienSpeed = 1;
    private int niveau;
    
    
    private ImageIcon alienIcon;
    private Image backgroundImage;
    private boolean custom = false;
 
    public GameController() {
        
        this.backgroundImage = new ImageIcon(this.getClass().getClassLoader().getResource("background.png")).getImage();
        this.alienIcon = new ImageIcon(this.getClass().getClassLoader().getResource("fox.png"));
        this.spaceShip = new SpaceShip(400.0, 580.0, 15, new ImageIcon(this.getClass().getClassLoader().getResource("spaceshipPiou.png")));

        this.niveau = 1;
        this.aliens = new ArrayList();
        this.buildings = new ArrayList<>();
        this.player = new Player(0, 3, "BestPlayer");
        initGameControllerObjects();
        this.timer = new Timer(5, this);
        this.timer.start();
    }

    private void initGameControllerObjects() {
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
        this.isGameIsOver = false;
        this.aliens .clear();
        buildAliensList();
        this.timer.restart();
    }

    public void resetGameControllerWhenNewGame() {
        this.niveau = 1;
        this.buildings.clear();
        this.spaceShip.setX(400);
        this.alienSpaceShip = null;
        this.nbChancesSpawnVaisseau = 10000;
        if(this.spaceShip.getBullet() != null)this.spaceShip.getBullet().onCollision();
        
        buildBuildingList();
        this.resetGameController();
        
        this.player = new Player(0, 3, "BestPlayer");
        
    }

    /**
     * @param aliens the aliens to set
     */
    public void setAliens(List<List<Alien>> aliens) {
        this.aliens = aliens;
    }

    public void actionJoueur(){
        if(!this.pause){
            if(getArrowTouch() != 0){
                this.getSpaceShip().move(getArrowTouch() == -1);
            }
            if(isSpaceTouch()){
                this.getSpaceShip().shoot();
            }
                    
        }
        this.notifier();
    }
    
    private void buildAliensList(){
        List<Alien> listAliens;
        
        for(int x = 1;x<getNbAliensLigne()+1;x++){
            listAliens = new ArrayList<>();
            for(int y = 2;y<getNbAliensColonnes()+2;y++){
                listAliens.add(new Alien(x*60, y*50, getAlienSpeed(), 10, this.alienIcon));
            }   
            this.aliens.add(listAliens);
        }
    }
    
    public void changementDeNiveau(){
        Random rand = new Random();
        this.niveau +=1;
        if(!custom){
            this.setNbAliensColonnes(rand.nextInt()%(this.niveau%8 +1) + 2);
            this.setNbAliensLigne(rand.nextInt()%(this.niveau%5 +1) + 2);
            this.setNbChancesBulletAlien((rand.nextInt())%(30000 - niveau*1000 +1) +5000) ;
        }
    }
    
    private void buildBuildingList(){
        if(getNbBuilding() !=0){
            double x = 800 / (getNbBuilding()) - 29.5;
            for(int i = 1; i<getNbBuilding()+1;i++){
                buildings .add(new Building(x*i, 500, 6,new ImageIcon(this.getClass().getClassLoader().getResource("poulailler.png"))));
            } 
        }
        
    }
    public void notifier(){
        this.setChanged();
        this.notifyObservers();
    }

    public void gameOver() {
        this.isGameIsOver = true;
        this.notifier();
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
                    if(rand.nextInt()%getNbChancesBulletAlien() == 0){
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
                        this.gameOver();
                        break;
                    }
                }
            }
            
        }
        
        if(getNbChancesSpawnVaisseau() != 0 && rand.nextInt()%getNbChancesSpawnVaisseau() == 0){
            this.setAlienSpaceShip(new AlienSpaceShip(10, 55, 1, 100, new ImageIcon(this.getClass().getClassLoader().getResource("spaceShipAlien.png"))));
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
                if(this.getAlienSpaceShip().getY()>600){          
                    this.gameOver();
                }
            }
        }
    }
         
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!this.aliens.isEmpty() && !this.aliens.get(this.aliens.size() - 1).isEmpty() &&!isGameIsOver()){
            moveAliens();
            if(this.getSpaceShip().getBullet() != null){
                this.getSpaceShip().getBullet().move(true);
            }     
            this.notifier();
        }else if(!isGameIsOver()){
            changementDeNiveau();
            resetGameController();
        }else{
            this.pauseGame();
        }
    }
    
    public void setBulletSprite(ImageIcon imageIcon,int width,int height){
        this.getSpaceShip().setBulletIcon(imageIcon);
        this.getSpaceShip().setWidthBullet(width);
        this.getSpaceShip().setHeightBullet(height);
        
        if(this.spaceShip.getBullet()!=null){
            this.getSpaceShip().getBullet().setSprite(imageIcon);
            this.getSpaceShip().getBullet().setWidth(width);
            this.getSpaceShip().getBullet().setHeight(height);
        }
        this.notifier();
        
    }
    
    public void setAliensSprite(ImageIcon imageIcon){
        this.alienIcon = imageIcon;
        for(int j = 0;j<aliens.size();j++){
            for(int i = 0;i<aliens.get(j).size();i++){
                aliens.get(j).get(i).setSprite(imageIcon);
            }
        }
        this.notifier();
        
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

    public Timer getTimer() {
        return timer;
    }

    /**
     * @param nbChancesSpawnVaisseau the nbChancesSpawnVaisseau to set
     */
    public void setNbChancesSpawnVaisseau(int nbChancesSpawnVaisseau) {
        this.nbChancesSpawnVaisseau = nbChancesSpawnVaisseau;
    }

    public void setGameIsOver(boolean gameIsOver) {
        this.pauseGame();
        isGameIsOver = gameIsOver;
    }

    /**
     * @return the spaceTouch
     */
    public boolean isSpaceTouch() {
        return spaceTouch;
    }

    /**
     * @param spaceTouch the spaceTouch to set
     */
    public void setSpaceTouch(boolean spaceTouch) {
        this.spaceTouch = spaceTouch;
    }

    /**
     * @return the arrowTouch
     */
    public int getArrowTouch() {
        return arrowTouch;
    }

    /**
     * @param arrowTouch the arrowTouch to set
     */
    public void setArrowTouch(int arrowTouch) {
        this.arrowTouch = arrowTouch;
    }

    /**
     * @return the backgroundImage
     */
    public Image getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * @param backgroundImage the backgroundImage to set
     */
    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        this.notifier();
    }

    /**
     * @return the custom
     */
    public boolean isCustom() {
        return custom;
    }

    /**
     * @param custom the custom to set
     */
    public void setCustom(boolean custom) {
        this.custom = custom;
    }
        /**
     * @return the aliens
     */
    public List<List<Alien>> getAliens() {
        return aliens;
    }

    /**
     * @return the nbAliensLigne
     */
    public int getNbAliensLigne() {
        return nbAliensLigne;
    }

    /**
     * @param nbAliensLigne the nbAliensLigne to set
     */
    public void setNbAliensLigne(int nbAliensLigne) {
        this.nbAliensLigne = nbAliensLigne;
    }

    /**
     * @return the nbAliensColonnes
     */
    public int getNbAliensColonnes() {
        return nbAliensColonnes;
    }

    /**
     * @param nbAliensColonnes the nbAliensColonnes to set
     */
    public void setNbAliensColonnes(int nbAliensColonnes) {
        this.nbAliensColonnes = nbAliensColonnes;
    }

    /**
     * @return the nbChancesBulletAlien
     */
    public int getNbChancesBulletAlien() {
        return nbChancesBulletAlien;
    }

    /**
     * @param nbChancesBulletAlien the nbChancesBulletAlien to set
     */
    public void setNbChancesBulletAlien(int nbChancesBulletAlien) {
        this.nbChancesBulletAlien = nbChancesBulletAlien;
    }

    /**
     * @return the alienSpeed
     */
    public int getAlienSpeed() {
        return alienSpeed;
    }

    /**
     * @param alienSpeed the alienSpeed to set
     */
    public void setAlienSpeed(int alienSpeed) {
        this.alienSpeed = alienSpeed;
    }
    
}
