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

    /* Variables contenant les informations des appuis du joueurs */
    private boolean spaceTouch = false; // true si appui sur espace
    private int arrowTouch = 0; // -1 pour gauche / 0 pour rien / 1 pour droite

    /*Variables qui contiennent les objets du jeu */
    private Player player; // L'instance de joueur
    private SpaceShip spaceShip; // Le vaisseau
    private List<Building> buildings; // La liste des batiments
    private List<List<Alien>> aliens; // Une liste de listes de colonnes d'aliens
    private AlienSpaceShip alienSpaceShip; // Le vaisseau alien
    
    private final Timer timer; // Le timer qui permet de gérer les déplacements des objets
    
    /* Les variables permettant de gérer les collisions contre les murs*/
    private int isAliensOnTheWall = 1; // 1 si les aliens doivent aller à droite, -1 sinon
    private int isAlienSpaceShipOnTheWall = 1; // 1 si le vaisseau alien doit aller à droite, -1 sinon
    
    /* Les deux variables d'état du jeu */
    private boolean pause = false; // Si le jeu est en pause ou non
    private boolean isGameIsOver = false; // Si le joueur a perdu ou non 

    private int niveau; // Le niveau atteint par le joueur
    
    /*Les variables customisées en fonction des niveaux ou des préferences du joueurs*/
    private boolean custom = false; //Si oui on non on prend en compte les préferences du joueur en changeant de niveau
    private int nbAliensLigne = 2; //Le nombre d'aliens par ligne
    private int nbAliensColonnes = 2; //Le nombre d'aliens par colonne
    private final int nbBuilding = 4; // Le nombre de batiments 
    private int nbChancesBulletAlien = 2500; // Le nombre de chances qu'a un alien de tirer. Plus le nombre est petit, plus la chance est grande.
    private int nbChancesSpawnVaisseau = 10000; // Le nombre de chances qu'a un vaisseau alien d'apparaître. Plus le nombre est petit, plus la chance est grande. Lorsqu'un vaisseau alien est sur l'écran, le nombre passe à zéro car il ne peut y en avoir deux en même temps.
    private int alienSpeed = 1;//La vitesse des aliens, plus le nombre est grand, plus la vitesse est élevée.
    
    /*Deux images plus facilement stockées ici par soucis de facilité lors du chnagement dans les préférences.*/
    private ImageIcon alienIcon; //L'image des aliens à l'écran. 
    private Image backgroundImage; // l'image de fond.
    
    /* La classe principal du projet, le controller qui gère le lien entre les vues et le modèle . */
    
    public GameController() {
        /* Initialisation des variables qui ne se réinitialise pas lors d'une nouvelle partie / niveau */
        this.backgroundImage = new ImageIcon(this.getClass().getClassLoader().getResource("background.png")).getImage();
        this.alienIcon = new ImageIcon(this.getClass().getClassLoader().getResource("fox.png"));
        this.spaceShip = new SpaceShip(400.0, 580.0, 15, new ImageIcon(this.getClass().getClassLoader().getResource("spaceshipPiou.png")));
        this.player = new Player(0, 3, "BestPlayer");
        
        /* Initialisation du niveau et instanciation des listes*/
        this.niveau = 1;
        this.aliens = new ArrayList();
        this.buildings = new ArrayList<>();
        
        /*Initialisation des listes */
        this.buildAliensList();
        this.buildBuildingList();
        
        /*Initialisation du timer avec un delay de 5ms */
        this.timer = new Timer(5, this);
        this.timer.start(); // Départ du timer
    }
    
    /*Fonction de mise en pause du jeu, lorsque la jeu est en pause et que la fonction est appelée, il se relance et inversement.*/
    public void pauseGame() {
        if (this.pause) {
            this.timer.start();
        } else {
            this.timer.stop();
        }
        this.pause = !pause;
    }

    /* Fonction permettant de reinitialiser les objets avec des nouvelles valeurs lors d'un changement de niveau */
    public void resetGameController() {
        /* remise à zéro des aliens */
        this.isAliensOnTheWall = 1;
        this.aliens.clear();
        buildAliensList();
        
        //On redémarre le jeu
        this.pause = false;
        this.isGameIsOver = false;
        this.timer.restart();
    }
    
    /* Fonction permettant de reinitialiser les objets avec des nouvelles valeurs lors d'une nouvelle partie */
    public void resetGameControllerWhenNewGame() {
        
        /*Remise à zéro des variables concernant le jeu*/
        this.player = new Player(0, 3, "BestPlayer");
        this.niveau = 1;
        
        /* Réinitialisation des coordonnées et des chances de départ */
        this.spaceShip.setX(400);
        this.nbChancesSpawnVaisseau = 10000;
        
        /* Suppression des objets non-nécessaires*/
        this.alienSpaceShip = null;
        if(this.spaceShip.getBullet() != null)this.spaceShip.getBullet().onCollision();
        if(!custom){
            this.nbAliensLigne = 2; //Le nombre d'aliens par ligne
            this.nbAliensColonnes = 2; //Le nombre d'aliens par colonne
            this.nbChancesBulletAlien = 2500; // Le nombre de chances qu'a un alien de tirer. Plus le nombre est petit, plus la chance est grande.
            this.nbChancesSpawnVaisseau = 10000; // Le nombre de chances qu'a un vaisseau alien d'apparaître. Plus le nombre est petit, plus la chance est grande. Lorsqu'un vaisseau alien est sur l'écran, le nombre passe à zéro car il ne peut y en avoir deux en même temps.
            this.alienSpeed = 1;//La vitesse des aliens, plus le nombre est grand, plus la vitesse est élevée.
        }
        
        /*Réinitialisation des batiments */
        this.buildings.clear();
        buildBuildingList();
        
        
        /* Réinitialise les variables qui sont réinitialisées entre chaque niveau */
        this.resetGameController();
    }

    /*Gère les actions du joueur. Est appelée à chaque fois que le joueur tape, appuie ou lache une touche */
    public void actionJoueur(){
        if(!this.pause){ //On vérifie que le jeu n'est pas en pause
            if(getArrowTouch() != 0){ //On bouge si le joueur veut bouger
                this.getSpaceShip().move(getArrowTouch() == -1); //Si l'utilisateur veut aller à gaucher, move(true) sinon move(false)
            }
            if(isSpaceTouch()){ //Si il appuie sur espace, on essaye de tirer
                this.getSpaceShip().shoot();
            }
                    
        }
        this.notifier(); //On previent les observateurs
    }
    
    /* Fonction pour avertir les observateurs */
    public void notifier(){
        this.setChanged();
        this.notifyObservers();
    }
    
    /*Fonction qui previent que le jeu est terminé*/
    public void gameOver() {
        this.isGameIsOver = true;
        this.notifier();
    }
    
    /* Fonction appelée lors d'un changement de niveau.
        Rend le jeu plus dur en fonction de probabilités augmentées par les niveaux.
    */
    public void changementDeNiveau(){
        Random rand = new Random();
        this.niveau +=1; // On augmente le niveau
        if(!custom){
            this.setNbAliensColonnes(rand.nextInt()%(this.niveau%8 +1) + 2); //Les + x sont utilisés afin de ne pas avoir de 0
            this.setNbAliensLigne(rand.nextInt()%(this.niveau%5 +1) + 2);
            this.setNbChancesBulletAlien((rand.nextInt())%(30000 - niveau*1000 +1) +5000) ;
        }
    }
    
    /*Fonction de construction de la liste d'aliens */
    private void buildAliensList(){
        List<Alien> listAliens;
        
        for(int x = 1;x<getNbAliensLigne()+1;x++){ //Colonnes par colonnes
            listAliens = new ArrayList<>();
            for(int y = 2;y<getNbAliensColonnes()+2;y++){
                listAliens.add(new Alien(x*60, y*50, getAlienSpeed(), 10, this.alienIcon)); //On ajoute un alien à la colonne
            }   
            this.aliens.add(listAliens); //On ajoute la colonne à la liste
        }
    }
    

    /* Construction de la liste des batiments */
    private void buildBuildingList(){
        if(getNbBuilding() !=0){
            double x = 800 / (getNbBuilding()) - 29.5; //Gère l'espacement des batiments afin de les centrer
            for(int i = 1; i<getNbBuilding()+1;i++){
                buildings .add(new Building(x*i, 500, 6,new ImageIcon(this.getClass().getClassLoader().getResource("poulailler.png"))));
            } 
        }
        
    }
    
    /* Fonction permettant le déplacement des aliens, des missiles et l'apparition du vaisseau alien */
    private void moveAliens(){
        boolean shouldMoveDown = false; //Boolean qui dira si les aliens sont contre le mur ou non
        Random rand = new Random();
        
            if(this.aliens.get(this.aliens.size() - 1).get(0).getX()>856){ //Si les aliens touchent le mur droit
                this.isAliensOnTheWall = -1;
                shouldMoveDown = true;
            }else if(this.aliens.get(0).get(0).getX()<1){ //Sinon si ils touchent le mur droit
                this.isAliensOnTheWall = 1;
                shouldMoveDown = true;
            }

            this.aliens.forEach((ls) -> { //On parcourt la liste et on les fait tous avancer 
                ls.forEach((a) -> {
                    a.setX(a.getX() + a.getSpeed()*isAliensOnTheWall); //L'alien avance
                    if(rand.nextInt()%getNbChancesBulletAlien() == 0){ //Si le random % le nombre de chances est égal à zéro, on tire.
                        a.shoot();
                    }

                    if(a.getBullet()!= null){ //Si ils ont un missile, on le fait bouger aussi
                        a.getBullet().move(false); //Vers le bas
                    }
                });
            });
        
        if(shouldMoveDown){ //Si les aliens sont contre le mur
            for(int j = 0;j<aliens.size();j++){
                for(int i = 0;i<aliens.get(j).size();i++){
                    aliens.get(j).get(i).setY(aliens.get(j).get(i).getY() + 50); //Ils descendent
                    if(aliens.get(j).get(i).getY()>550){ //Si ils arrivent en bas
                        this.gameOver(); //C'est perdu !
                        break; //On sort de la boucle
                    }
                }
            }
            
        }
        
        if(getNbChancesSpawnVaisseau() != 0 && rand.nextInt()%getNbChancesSpawnVaisseau() == 0){ //Si le vaisseau n'est pas présent et que la chance le fait apparaitre
            this.setAlienSpaceShip(new AlienSpaceShip(10, 55, 1, 100, new ImageIcon(this.getClass().getClassLoader().getResource("spaceShipAlien.png"))));
            setNbChancesSpawnVaisseau(0); //Il est créé et on met la chance à zéro de l'obtenir
        }else if(getNbChancesSpawnVaisseau()==0){ //SI le vaisseau existe
            shouldMoveDown = false; //On le déplace sous le même principe que les aliens
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
    
    
    /*La fonction appelée par le timer toutes les 5ms */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!this.aliens.isEmpty() && !this.aliens.get(this.aliens.size() - 1).isEmpty() &&!isGameIsOver()){ //Si la liste n'est pas vide et que le jeu n'est pas fini
            moveAliens(); //On déplace les aliens
            if(this.getSpaceShip().getBullet() != null){ //On déplace le missile du joueur si il existe
                this.getSpaceShip().getBullet().move(true);
            }     
            this.notifier(); //On prévient les vues
        }else if(!isGameIsOver()){ //Si ce n'est pas la fin du jeu mais que les listes sont vides
            changementDeNiveau(); //On change de niveau
            resetGameController(); //On reset le gameController
        }else{
            this.pauseGame(); //Sinon on met le jeu en pause pour ne pas que le joueur puisse interferer avec l'écran alors qu'il à perdu
        }
    }
    
    /* Cette fonction sert à modifier l'image du missile du joueur lorsque désiré par l'utilisateur */
    public void setBulletSprite(ImageIcon imageIcon,int width,int height){
        this.getSpaceShip().setBulletIcon(imageIcon); // On modifie le sprite pour l'objet joueur
        this.getSpaceShip().setWidthBullet(width);// La largeur et la hauteur aussi pour adapter selon les sprites
        this.getSpaceShip().setHeightBullet(height);
        
        if(this.spaceShip.getBullet()!=null){ // On modifie le sprite pour l'instance du joueur 
            this.getSpaceShip().getBullet().setSprite(imageIcon);
            this.getSpaceShip().getBullet().setWidth(width);
            this.getSpaceShip().getBullet().setHeight(height);
        }
        this.notifier(); 
        
    }
    /* Cette fonction sert à modifier l'image des aliens lorsque désiré par l'utilisateur */
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
     * @param aliens the aliens to set
     */
    public void setAliens(List<List<Alien>> aliens) {
        this.aliens = aliens;
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
