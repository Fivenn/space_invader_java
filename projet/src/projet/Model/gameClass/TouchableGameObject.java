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
public abstract class TouchableGameObject extends DynamicGameObject{
    private Bullet bullet;
    
    public TouchableGameObject(double x, double y, int speed, ImageIcon sprite) {
        super(x, y, speed, sprite);
    }

    private void shoot(){
        this.bullet = new Bullet(this.x,this.y,this.speed);
    }
}
