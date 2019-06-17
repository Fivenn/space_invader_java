/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Model.gameClass;

import javax.swing.ImageIcon;


public class DynamicGameObject extends GameObject {
    double x;
    double y;
    int speed;

    public DynamicGameObject(double x, double y, int speed,ImageIcon sprite) {
        super(sprite);
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void move(boolean left) {
        if(left){
            x-=speed;
        }else{
            x+=speed;
        }
    }
    
}
