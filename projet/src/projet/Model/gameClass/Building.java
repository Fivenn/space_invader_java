/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Model.gameClass;

import javax.swing.ImageIcon;

/**
 *
 * @author mallou
 */
public class Building extends GameObject{
    private int lifePoints;

    public Building(double x, double y,int lifePoints, ImageIcon sprite) {
        super(x,y,sprite);
        this.width = 57;
        this.heigth = 60;
        this.lifePoints = lifePoints;
    }

    public void onCollision(){
        this.removeLifePoints(1);
        if(lifePoints>0){
            this.setSprite(new ImageIcon(this.getClass().getClassLoader().getResource("poulailler"+this.lifePoints+".png")));
        }else{
            this.setWidth(0);
            this.setHeigth(0);
        }
    }
    /**
     * @return the lifePoints
     */
    public int getLifePoints() {
        return lifePoints;
    }
    
    
    public void removeLifePoints(int lifePoints){
        this.lifePoints -= lifePoints;
    }
    /**
     * @param lifePoints the lifePoints to set
     */
    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }
}
