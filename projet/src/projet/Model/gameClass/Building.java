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
public class Building extends GameObject{
    private final int lifePoints;

    public Building(int lifePoints, ImageIcon sprite) {
        super(sprite);
        this.lifePoints = lifePoints;
    }
}
