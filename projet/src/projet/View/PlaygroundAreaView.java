package projet.View;

import projet.Controller.GameController;
import projet.Model.gameClass.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class PlaygroundAreaView extends JPanel implements Observer {
    private GameController gameController;
    private List<List<Alien>> alienList;
    private AlienSpaceShip alienSpaceShip;
    private List<Building> buildingList;
    private SpaceShip spaceShip;

    public PlaygroundAreaView(GameController gameController) {
        this.gameController = gameController;
        alienList = gameController.getAliens();
        alienSpaceShip = gameController.getAlienSpaceShip();
        buildingList = gameController.getBuildings();
        spaceShip = gameController.getSpaceShip();

        this.alienList = gameController.getAliens();
        this.alienSpaceShip = gameController.getAlienSpaceShip();
        this.buildingList = gameController.getBuildings();
        this.spaceShip = gameController.getSpaceShip();

        this.gameController.addObserver(this);
        setBackground(Color.BLACK);

        InformationGameAreaView informationGameAreaView = new InformationGameAreaView(gameController);

        this.setPreferredSize(new Dimension(900, 1000));
        this.setLayout(new BorderLayout());

        this.add(informationGameAreaView, BorderLayout.NORTH);
        
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        drawSpaceShip(g);
        drawAliens(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawBuildings(Graphics g){
        for(GameObject go : buildingList){
            g.drawImage(go.loadImage(),(int) go.getX(), (int) go.getY(), this);
        }     
    }
    
    private void drawAliens(Graphics g){
        for(List<Alien> ls : this.alienList){
            
            for(GameObject go : ls){
                System.out.println("aliens x : "+go.getX()+" y : "+go.getY());
                g.drawImage(go.loadImage(),(int) go.getX(), (int) go.getY(),50,50, this);
            }
        }
    }
    
    private void drawSpaceShip(Graphics g){
        Image i = this.spaceShip.loadImage();
        g.drawImage(i,(int) this.spaceShip.getX(), (int) this.spaceShip.getY(),50,50, this);
        // System.out.println("projet.View.PlaygroundAreaView.drawSpaceShip()"+this.spaceShip.loadImage());
    }

    private void drawAlienSpaceShip(Graphics g){
        Image i = this.alienSpaceShip.loadImage();
        g.drawImage(i, (int) this.alienSpaceShip.getX(), (int) this.alienSpaceShip.getY(), 50, 50, this);
    }

    private void drawBullet(Graphics g) {
        Image i = this.spaceShip.getBullet().loadImage();
        g.drawImage(i, (int) this.spaceShip.getBullet().getX(), (int) this.spaceShip.getBullet().getY(), 200, 200, this);
    }
        
    @Override
    public void update(Observable o, Object arg) {
        repaint(); 
    }

}