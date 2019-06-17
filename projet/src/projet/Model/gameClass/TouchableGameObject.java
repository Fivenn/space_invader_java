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
    
    protected void shoot(int speedBullet,String bulletSprite){
        this.bullet = new Bullet(getX(), getY(), speedBullet, new ImageIcon(bulletSprite));
    }

}
