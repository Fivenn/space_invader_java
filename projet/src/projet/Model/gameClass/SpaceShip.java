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
public class SpaceShip extends TouchableGameObject{
    private boolean bulletReady;
    
    public SpaceShip(double x, double y, int speed, ImageIcon sprite) {
        super(x, y, speed,sprite);
    }

    /**
     * @return the bulletReady
     */
    public boolean isBulletReady() {
        return bulletReady;
    }

    /**
     * @param bulletReady the bulletReady to set
     */
    public void setBulletReady(boolean bulletReady) {
        this.bulletReady = bulletReady;
    }
    
    public void shoot(){
        if(isBulletReady()){
            super.shoot(100,"src/projet/Ressources/shot.gif");
        }
    }
}
