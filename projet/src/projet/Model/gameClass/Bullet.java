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
    
    public Bullet(double x, double y, int speed, ImageIcon sprite, TouchableGameObject shooter) {
        super(x, y, speed, sprite);
        this.shooter = shooter;
    }
    
    private void onCollision(){
        this.shooter.setBullet(null);
        this.shooter.setBulletReady(true);
    }
    
    @Override
    public void move(boolean up) {
        if(up){
            setY(getY() - getSpeed());
        }else{
            setY(getY() + getSpeed());
        }
        if(this.getY()<0){
            this.onCollision();
        }
    }
    
}
