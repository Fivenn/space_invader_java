package projet.View;

import com.sun.tools.javac.Main;
import projet.Controller.GameController;
import projet.Model.gameClass.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class PlaygroundAreaView extends JPanel implements Observer {
    private final GameController gameController; // COntrôleur du jeu
    private List<List<Alien>> alienList; // Liste d'aliens
    private List<Building> buildingList; // Liste de batiments

    /* Classe définissant la vue du plateau de jeu */
    public PlaygroundAreaView(GameController gameController) {        
        /* Initialisation des objets utiles à la vue + configuration */
        this.gameController = gameController;
        alienList = gameController.getAliens();
        buildingList = gameController.getBuildings();
        
        this.gameController.addObserver(this);
        setBackground(Color.BLACK);

        InformationGameAreaView informationGameAreaView = new InformationGameAreaView(this.gameController);


        this.setPreferredSize(new Dimension(900, 1000));
        this.setLayout(new BorderLayout());

        this.add(informationGameAreaView, BorderLayout.NORTH);
    }

    /* Fonction permettant d'afficher les différents composants du jeu sur le plateau de jeu */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.gameController.getBackgroundImage(),0,0, 1000, 800, this); // Affichage du background
        draw(g); // Dessine les différents éléments du plateau de jeu
    }

    /* Fonction permettant de dessiner les différents éléments du plateau de jeu */
    private void draw(Graphics g) {
        drawBuildings(g);
        drawSpaceShip(g);
        drawAliens(g); 
        
        if(this.gameController.getSpaceShip().getBullet()!= null){
            drawBullet(g);
        }
        
        if(this.gameController.getNbChancesSpawnVaisseau() == 0){
            drawAlienSpaceShip(g);
        }
        drawAliensBullets(g);
        
        
        Toolkit.getDefaultToolkit().sync();
    }

    /* Fonction permettant de dessiner les différents batiments */
    private void drawBuildings(Graphics g){
        buildingList.forEach((go) -> {
            g.drawImage(go.loadImage(),(int) go.getX(), (int) go.getY(), (int) go.getWidth(), (int) go.getHeight(), this);
        });     
    }

    /* Fonction permettant de dessiner les aliens */
    private void drawAliens(Graphics g){
        this.alienList.forEach((ls) -> {
            ls.forEach((go) -> {
                g.drawImage(go.loadImage(),(int) go.getX(), (int) go.getY(), (int) go.getWidth(), (int) go.getHeight(), this);
            });
        });
    }

    /* Fonction permettant de dessiner le spaceship */
    private void drawSpaceShip(Graphics g){
        g.drawImage(this.gameController.getSpaceShip().loadImage(),(int) this.gameController.getSpaceShip().getX(), (int) this.gameController.getSpaceShip().getY(), (int) this.gameController.getSpaceShip().getWidth(), (int) this.gameController.getSpaceShip().getHeight(), this);
    }

    /* Fonction permettant de dessiner le vaisseau alien */
    private void drawAlienSpaceShip(Graphics g){
        Image i = this.gameController.getAlienSpaceShip().loadImage();
        g.drawImage(i, (int) this.gameController.getAlienSpaceShip().getX(), (int) this.gameController.getAlienSpaceShip().getY(), (int) this.gameController.getAlienSpaceShip().getWidth(), (int) this.gameController.getAlienSpaceShip().getHeight(), this);
    }

    /* Fonction permettant de dessiner un missile du vaisseau */
    private void drawBullet(Graphics g) {
        Image i = this.gameController.getSpaceShip().getBullet().loadImage();
        g.drawImage(i, (int) this.gameController.getSpaceShip().getBullet().getX(), (int) this.gameController.getSpaceShip().getBullet().getY(), (int) this.gameController.getSpaceShip().getBullet().getWidth(), (int) this.gameController.getSpaceShip().getBullet().getHeight(), this);
        this.checkCollision(gameController.getSpaceShip().getBullet());
    }

    /* Fonction permettant de dessiner un missile d'un alien */
    private void drawAliensBullets(Graphics g){
        Image i;

        for(List<Alien> l : this.alienList){
            for(Alien a: l){
                if(a.getBullet()!= null){
                    i = a.getBullet().loadImage();
                    g.drawImage(i, (int) a.getBullet().getX(), (int) a.getBullet().getY(), (int) a.getBullet().getWidth(), (int) a.getBullet().getHeight(), this);
                    this.checkCollision(a.getBullet());  
                }
            }
        }
    }

    /* Fonction permettant de vérifier la collision entre un missile et un alien/spaceship */
    public void checkCollision(Bullet dgo){
        /* On utilise dans cette fonction la fonction intersect qui permet de vérifier si
        * deux rectangles sont en collisions. Pour cela, on dessine des rectangles invisibles
        * sur chaque élément du jeu. Si un missile du spaceship rentre en collision avec un alien (alien spaceship y compris-
        * alors on le supprime du plateau de jeu. Si un missile alien rentre en collision avec le spaceship alors
        * on enlève un point de vie au player.
         */
        List<Alien> l;
        Rectangle dgoR = new Rectangle((int)dgo.getX(),(int) dgo.getY(), (int)dgo.getWidth(), (int)dgo.getHeight());
        
        for(int i = 0; i<this.gameController.getNbBuilding();i++){
            if(this.buildingList.size()>=0 && dgoR.intersects(new Rectangle((int) this.buildingList.get(i).getX(), (int) this.buildingList.get(i).getY(),(int) this.buildingList.get(i).getWidth(), (int)this.buildingList.get(i).getHeight()))){
                dgo.onCollision();
                this.buildingList.get(i).onCollision();
            } 
        }
        
        if(dgo.getShooter()== this.gameController.getSpaceShip()){
            for(int i = 0;i<alienList.size();i++){
                l = this.alienList.get(i);
                if(l.size()-1>=0 && dgoR.intersects(new Rectangle((int)l.get(l.size()-1).getX(),(int) l.get(l.size()-1).getY(), (int)l.get(l.size()-1).getWidth(), (int)l.get(l.size()-1).getHeight()))){
                    dgo.onCollision();
                    this.gameController.getPlayer().addPoints(this.alienList.get(i).get(l.size()-1).getPoints());
                    this.alienList.get(i).remove(l.size()-1);
                    if(this.alienList.get(i).isEmpty()){
                        this.alienList.remove(i);
                    }
                    break; 
                }
                for(Alien a: l){
                    if(a.getBullet()!= null){
                        if(dgoR.intersects(new Rectangle((int)a.getBullet().getX(),(int) a.getBullet().getY(), (int)a.getBullet().getWidth(), (int)a.getBullet().getHeight()))){
                            a.getBullet().onCollision();
                            dgo.onCollision();
                        }
                    }
                }
                
            }
            if(this.gameController.getNbChancesSpawnVaisseau()==0 && dgoR.intersects(new Rectangle((int) this.gameController.getAlienSpaceShip().getX(), (int) this.gameController.getAlienSpaceShip().getY(), (int) this.gameController.getAlienSpaceShip().getWidth(), (int) this.gameController.getAlienSpaceShip().getHeight()))){
                dgo.onCollision();
                this.gameController.getAlienSpaceShip().removeLifePoints(1);
                if(this.gameController.getAlienSpaceShip().getLifePoints() == 0){
                    this.gameController.getPlayer().addPoints(this.gameController.getAlienSpaceShip().getPoints());
                    this.gameController.setAlienSpaceShip(null);
                    this.gameController.setNbChancesSpawnVaisseau(1000);
                }
            }
            
        }

        if(dgo.getShooter() != this.gameController.getSpaceShip() && dgoR.intersects(new Rectangle((int) this.gameController.getSpaceShip().getX(), (int) this.gameController.getSpaceShip().getY(),(int) this.gameController.getSpaceShip().getWidth(), (int)this.gameController.getSpaceShip().getHeight()))){
                dgo.onCollision();
                this.gameController.getPlayer().removeLifePoints(1);
                if(gameController.getPlayer().getLifePoints() == 0){
                    this.gameController.gameOver();
                }
        }
    }
        
    @Override
    /* Fonction permettant de "redessiner le plateau de jeu dès que l'on reçoit une
    * notification de l'observalbe.s
     */
    public void update(Observable o, Object arg) {
        if(!this.gameController.isGameIsOver()){
            repaint();
        }
    }

}