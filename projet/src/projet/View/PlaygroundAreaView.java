package projet.View;

import projet.Controller.GameController;
import projet.Model.gameClass.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class PlaygroundAreaView extends JPanel implements Observer {
    private GameController gameController;
    private List<Alien> alienList;
    private AlienSpaceShip alienSpaceShip;
    private List<Building> buildingList;
    private SpaceShip spaceShip;

    public PlaygroundAreaView(GameController gameController) {
        this.gameController = gameController;
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
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawBuildings(Graphics g){
        for(GameObject go : buildingList){
            g.drawImage(go.loadImage(),(int) go.getX(), (int) go.getY(), this);
        }     
    }
    
    private void drawAliens(Graphics g){
        for(GameObject go : alienList){
            g.drawImage(go.loadImage(),(int) go.getX(), (int) go.getY(), this);
        }
    }
    
    private void drawSpaceShip(Graphics g){
        Image i = this.spaceShip.loadImage();
        g.drawImage(i,(int) this.spaceShip.getX(), (int) this.spaceShip.getY(),50,50, this);
        System.out.println("x : "+this.spaceShip.getX() + " int x : "+(int)this.spaceShip.getX());
    }
        
    @Override
    public void update(Observable o, Object arg) {
        repaint(); 
    }

}