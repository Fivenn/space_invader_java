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
    
    public Bullet(double x, double y, int speed) {
        super(x, y, speed, new ImageIcon("bulletImage"));
    }
    
}
