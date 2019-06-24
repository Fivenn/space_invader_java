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

/* Cette classe est la classe batiment. Elle définit des objets visibles mais non déplacables.
    - Elle possède un attribut lifePoints qui contient le nombre de points de vie du batiment
    - La fonction onCollision permet de redéfinir le sprite du batiment en fonction des dégats qu'il a reçu.
            * Le batiment n'est pas détruit lorsqu'il tombe à 0PV pour ne pas créer de problèmes dans la gestion des autres.
*/

public class Building extends GameObject{
    private int lifePoints;

    public Building(double x, double y,int lifePoints, ImageIcon sprite) {
        super(x,y,sprite);
        this.width = 57;
        this.height = 60;
        this.lifePoints = lifePoints;
    }

    public void onCollision(){
        this.removeLifePoints(1);
        if(lifePoints>0){
            this.setSprite(new ImageIcon(this.getClass().getClassLoader().getResource("poulailler"+this.lifePoints+".png")));
        }else{
            this.setWidth(0);
            this.setHeight(0);
        }
    }
    /**
     * @return the lifePoints
     */
    public int getLifePoints() {
        return lifePoints;
    }
    
    
    public void removeLifePoints(int lifePoints){
        this.lifePoints -= lifePoints;
    }
    /**
     * @param lifePoints the lifePoints to set
     */
    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }
}
