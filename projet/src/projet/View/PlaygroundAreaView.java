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
    Image spaceShipImage;
    JLabel gamerOverLabel;
    
    public PlaygroundAreaView(GameController gameController) {
        this.gameController = gameController;
        alienList = gameController.getAliens();
        alienSpaceShip = gameController.getAlienSpaceShip();
        buildingList = gameController.getBuildings();
        spaceShipImage = this.gameController.getSpaceShip().loadImage();

        this.gameController.addObserver(this);
        setBackground(Color.BLACK);

        InformationGameAreaView informationGameAreaView = new InformationGameAreaView(gameController);

        gamerOverLabel = new JLabel("GAME OVER");
        gamerOverLabel.setForeground(Color.white);
        gamerOverLabel.setBackground(Color.black);
        gamerOverLabel.setFont(gamerOverLabel.getFont().deriveFont(64f));

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
        drawBuildings(g);
        drawSpaceShip(g);
        drawAliens(g); 
        if(this.gameController.getSpaceShip().getBullet()!= null){
            drawBullet(g);
        }
        drawAliensBullets(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawBuildings(Graphics g){
        buildingList.forEach((go) -> {
            g.drawImage(go.loadImage(),(int) go.getX(), (int) go.getY(), (int) go.getWidth(), (int) go.getHeigth(), this);
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
        
        g.drawImage(spaceShipImage,(int) this.gameController.getSpaceShip().getX(), (int) this.gameController.getSpaceShip().getY(), (int) this.gameController.getSpaceShip().getWidth(), (int) this.gameController.getSpaceShip().getHeigth(), this);
    }

    private void drawAlienSpaceShip(Graphics g){
        Image i = this.alienSpaceShip.loadImage();
        g.drawImage(i, (int) this.alienSpaceShip.getX(), (int) this.alienSpaceShip.getY(), (int) this.alienSpaceShip.getWidth(), (int) this.alienSpaceShip.getWidth(), this);
    }

    private void drawBullet(Graphics g) {
        Image i = this.gameController.getSpaceShip().getBullet().loadImage();
        g.drawImage(i, (int) this.gameController.getSpaceShip().getBullet().getX(), (int) this.gameController.getSpaceShip().getBullet().getY(), (int) this.gameController.getSpaceShip().getBullet().getWidth(), (int) this.gameController.getSpaceShip().getBullet().getHeigth(), this);
        this.checkCollision(gameController.getSpaceShip().getBullet());
    }
    
    private void drawAliensBullets(Graphics g){
        Image i;

        for(List<Alien> l : this.alienList){
            for(Alien a: l){
                if(a.getBullet()!= null){
                    i = a.getBullet().loadImage();
                    g.drawImage(i, (int) a.getBullet().getX(), (int) a.getBullet().getY(), (int) a.getBullet().getWidth(), (int) a.getBullet().getHeigth(), this);
                    this.checkCollision(a.getBullet());  
                }
            }
        }
    }
    public void checkCollision(Bullet dgo){
        
        List<Alien> l;
        Rectangle dgoR = new Rectangle((int)dgo.getX(),(int) dgo.getY(), (int)dgo.getWidth(), (int)dgo.getHeigth());
        
        for(int i = 0; i<this.gameController.getNbBuilding();i++){
            if(this.buildingList.size()>=0 && dgoR.intersects(new Rectangle((int) this.buildingList.get(i).getX(), (int) this.buildingList.get(i).getY(),(int) this.buildingList.get(i).getWidth(), (int)this.buildingList.get(i).getHeigth()))){
                dgo.onCollision();
                this.buildingList.get(i).onCollision();
            } 
        }
        
        if(dgo.getShooter()== this.gameController.getSpaceShip()){
            for(int i = 0;i<alienList.size();i++){
                l = this.alienList.get(i);
                if(l.size()-1>=0 && dgoR.intersects(new Rectangle((int)l.get(l.size()-1).getX(),(int) l.get(l.size()-1).getY(), (int)l.get(l.size()-1).getWidth(), (int)l.get(l.size()-1).getHeigth()))){
                    dgo.onCollision();
                    this.gameController.getPlayer().addPoints(this.alienList.get(i).get(l.size()-1).getPoints());
                    this.alienList.get(i).remove(l.size()-1);
                    if(this.alienList.get(i).isEmpty()){
                        this.alienList.remove(i);
                    }
                    break; 
                }
            }
        }

        if(dgo.getShooter() != this.gameController.getSpaceShip() && dgoR.intersects(new Rectangle((int) this.gameController.getSpaceShip().getX(), (int) this.gameController.getSpaceShip().getY(),(int) this.gameController.getSpaceShip().getWidth(), (int)this.gameController.getSpaceShip().getHeigth()))){
                dgo.onCollision();
                this.gameController.getPlayer().removeLifePoints(1);
        }
    }
        
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

}