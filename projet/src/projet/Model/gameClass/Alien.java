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

/* Cette classe est la classe Alien. Elle définit des objets visibles, déplacables pouvant tirer.
    - Elle possède un attribut points qui contient le nombre de points accordés au joueur en cas de décès.
    - La fonction shoot redéfinit celle que touchableObject et permet de tirer en définissant les attributs du missile.
*/

public class Alien extends TouchableGameObject{
    private int points;
    
    public Alien(double x, double y, int speed, int points, ImageIcon sprite) {
        super(x, y, speed,sprite,50,50);
        this.points = points;
    }
    

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }
    
    public void shoot(){
        if(isBulletReady()){
            super.shoot(1,10,10,new ImageIcon(this.getClass().getClassLoader().getResource("shotAlien.png")));
            this.setBulletReady(false);
        }
    }
}
