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

/* Cette classe est la classe missile. Elle définit des objets visibles et déplacables.
    - Elle possède un attribut shooter qui contient l'objet qui lance le missile.
    - La fonction onCollision permet de reinitialiser les paramètres de missile du tireur.
    - La fonction move est redéfinie car celle présente dans dynamic object ne permet que de se déplacer de gauche à droite.
            * Cette fonction permet au missible d'aller soit vers le haut soit vers le bas selon qui le tire.
*/
public class Bullet extends DynamicGameObject{


    private TouchableGameObject shooter;
    
    public Bullet(double x, double y, double width,double heigth, int speed, ImageIcon sprite, TouchableGameObject shooter) {
        super(speed, sprite,x, y, width,heigth);
        this.shooter = shooter;
    }

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
