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

/* Cette classe est la classe AlienSpaceShip. Elle définit des objets visibles et déplacables. 
            * La fonction de tir n'est pas utilisée pour le moment mais peut facilement être implémentée, la classe héritant de touchableGameObject.
    - La fonction remove lifePoints retire les points de vie selon le nombre demandé.
*/

public class AlienSpaceShip extends Alien{
    private int lifePoints;
    public AlienSpaceShip(double x, double y, int speed, int points, ImageIcon sprite) {
        super(x, y, speed, points, sprite);
        this.lifePoints = 3;
    }
    
    public void removeLifePoints(int lifePoints){
        this.lifePoints -= lifePoints;
    }
    /**
     * @return the lifePoints
     */
    public int getLifePoints() {
        return lifePoints;
    }

    /**
     * @param lifePoints the lifePoints to set
     */
    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }
    
    
    
}
