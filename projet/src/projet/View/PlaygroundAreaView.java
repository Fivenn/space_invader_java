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
    Image spaceShipImage;
    
    public PlaygroundAreaView(GameController gameController) {
        this.gameController = gameController;
        alienList = gameController.getAliens();
        alienSpaceShip = gameController.getAlienSpaceShip();
        buildingList = gameController.getBuildings();
        spaceShip = gameController.getSpaceShip();
        spaceShipImage = this.spaceShip.loadImage();
        
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
        
        if(this.gameController.getSpaceShip().getBullet()!= null){
            drawBullet(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawBuildings(Graphics g){
        buildingList.forEach((go) -> {
            g.drawImage(go.loadImage(),(int) go.getX(), (int) go.getY(), this);
        });     
    }
    
    private void drawAliens(Graphics g){
        this.alienList.forEach((ls) -> {
            ls.forEach((go) -> {
                g.drawImage(go.loadImage(),(int) go.getX(), (int) go.getY(), (int) go.getWidth(), (int) go.getHeigth(), this);
            });
        });
    }
    
    private void drawSpaceShip(Graphics g){
        
        g.drawImage(spaceShipImage,(int) this.spaceShip.getX(), (int) this.spaceShip.getY(), (int) this.spaceShip.getWidth(), (int) this.spaceShip.getHeigth(), this);
    }

    private void drawAlienSpaceShip(Graphics g){
        Image i = this.alienSpaceShip.loadImage();
        g.drawImage(i, (int) this.alienSpaceShip.getX(), (int) this.alienSpaceShip.getY(), (int) this.alienSpaceShip.getWidth(), (int) this.alienSpaceShip.getWidth(), this);
    }

    private void drawBullet(Graphics g) {
        Image i = this.spaceShip.getBullet().loadImage();
        g.drawImage(i, (int) this.spaceShip.getBullet().getX(), (int) this.spaceShip.getBullet().getY(), (int) this.spaceShip.getBullet().getWidth(), (int) this.spaceShip.getBullet().getHeigth(), this);
        this.checkCollision(spaceShip.getBullet());
    }
    
    public void checkCollision(DynamicGameObject dgo){
        
        List<Alien> l;
        Rectangle dgoR = new Rectangle((int)dgo.getX(),(int) dgo.getY(), (int)dgo.getWidth(), (int)dgo.getHeigth());

        for(int i = 0;i<alienList.size();i++){
            l = this.alienList.get(i);
            if(l.size()-1>=0 && dgoR.intersects(new Rectangle((int)l.get(l.size()-1).getX(),(int) l.get(l.size()-1).getY(), (int)l.get(l.size()-1).getWidth(), (int)l.get(l.size()-1).getHeigth()))){
                dgo.onCollision();
                this.alienList.get(i).remove(l.size()-1);
                if(this.alienList.get(i).isEmpty()){
                    this.alienList.remove(i);
                }
                break; 
            }

        }
        
    }
        
    @Override
    public void update(Observable o, Object arg) {
        repaint(); 
    }

}