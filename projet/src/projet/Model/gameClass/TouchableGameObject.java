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

    private boolean bulletReady;
    private Bullet bullet;
    
    public TouchableGameObject(double x, double y, int speed, ImageIcon sprite) {
        super(x, y, speed, sprite);
    }

    public TouchableGameObject( double x, double y, int speed, ImageIcon sprite,double width, double heigth) {
        super(speed, sprite, x, y, width, heigth);
        this.setBulletReady(true);
        this.bullet = null;
    }
    
    protected void shoot(int speedBullet, int width,int heigth,ImageIcon bulletSprite){
        this.setBullet(new Bullet(getX()+this.getWidth()/11, getY(),width,heigth, speedBullet, bulletSprite,this));
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
        /**
     * @return the bullet
     */
    public Bullet getBullet() {
        return bullet;
    }

    /**
     * @param bullet the bullet to set
     */
    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

}
