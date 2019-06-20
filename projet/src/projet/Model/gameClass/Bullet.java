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
public class Bullet extends DynamicGameObject{


    private TouchableGameObject shooter;
    
    public Bullet(double x, double y, double width,double heigth, int speed, ImageIcon sprite, TouchableGameObject shooter) {
        super(speed, sprite,x, y, width,heigth);
        this.shooter = shooter;
    }

    @Override
    public void onCollision(){
        this.getShooter().setBullet(null);
        this.getShooter().setBulletReady(true);
    }
    
        /**
     * @return the shooter
     */
    public TouchableGameObject getShooter() {
        return shooter;
    }

    /**
     * @param shooter the shooter to set
     */
    public void setShooter(TouchableGameObject shooter) {
        this.shooter = shooter;
    }
    @Override
    public void move(boolean up) {
        if(up){
            setY(getY() - getSpeed());
        }else{
            setY(getY() + getSpeed());
        }
        if(this.getY()<0 || this.getY()>650){
            this.onCollision();
        }
    }
    
}
