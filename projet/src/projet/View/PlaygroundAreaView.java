package projet.View;

import projet.Controller.GameController;
import projet.Model.gameClass.*;

import javax.swing.*;
import javax.swing.border.Border;
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
    
    public PlaygroundAreaView(GameController gameController) {
        this.gameController = gameController;
        alienList = gameController.getAliens();
        alienSpaceShip = gameController.getAlienSpaceShip();
        buildingList = gameController.getBuildings();
        spaceShipImage = this.gameController.getSpaceShip().loadImage();

        this.gameController.addObserver(this);
        setBackground(Color.BLACK);

        InformationGameAreaView informationGameAreaView = new InformationGameAreaView(gameController);

        JLabel gamerOver = new JLabel("GAME OVER");
        gamerOver.setForeground(Color.white);
        gamerOver.setBackground(Color.black);
        gamerOver.setFont(gamerOver.getFont().deriveFont(64f));

        this.setPreferredSize(new Dimension(900, 1000));
        this.setLayout(new BorderLayout());
        this.add(gamerOver, BorderLayout.CENTER);

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