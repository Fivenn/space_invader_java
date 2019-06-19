/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Model.gameClass;

import javax.swing.ImageIcon;


public class DynamicGameObject extends GameObject {
    private int speed;

    public DynamicGameObject(double x, double y, int speed,ImageIcon sprite) {
        super(x,y,sprite);
        this.speed = speed;
    }

    public DynamicGameObject(int speed, ImageIcon sprite, double x, double y, double width, double heigth) {
        super(sprite, x, y, width, heigth);
        this.speed = speed;
    }
    

    public void move(boolean left) {
        if(left){
            setX(getX() - getSpeed());
        }else{
            setX(getX() + getSpeed());
        }
    }

    

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
}
