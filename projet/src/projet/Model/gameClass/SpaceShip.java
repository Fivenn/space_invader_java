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
    
    
    public SpaceShip(double x, double y, int speed, ImageIcon sprite) {
        super(x, y, speed,sprite,50,50);
        
    }

    
    
    public void shoot(){
        if(isBulletReady()){
            super.shoot(10,new ImageIcon(this.getClass().getClassLoader().getResource("shot.gif")));
            this.setBulletReady(false);
        }
    }
}
