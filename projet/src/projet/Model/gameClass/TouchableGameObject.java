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


/* Cette classe est la classe permettant de gérer tous les objets pouvant tirer
    - Elle est fille de la classe dynamicGameObject car tous les objets gérés par cette classe sont visibles et déplaçable.
    - L'attribut bulletReady permet de savoir si l'objet à le droit de tirer à nouveau. Il est remis à zéro lorsque le missile à disparu de l'écran.
    - L'attribut Bullet est le missile lancé par l'objet. Il est instancié uniquement lorsqu'il devient visible et redevient nul sinon.
    - La fonction shoot sert à tirer. Elle instancie le missile au moment du tir selon les paramètres passés par la classe inférieure.
    - La classe possède deux constructeurs afin de ne pas avoir à définir la taille ou la hauteur dans le constructeur de chaque classe.
*/

public abstract class TouchableGameObject extends DynamicGameObject{

    private boolean bulletReady;
    private Bullet bullet;
    
    public TouchableGameObject(double x, double y, int speed, ImageIcon sprite) {
        super(x, y, speed, sprite);
    }

    public TouchableGameObject( double x, double y, int speed, ImageIcon sprite,double width, double heigth) {
        super(speed, sprite, x, y, width, heigth);
        this.setBulletReady(true);
        this.bullet = null;
    }
    
    protected void shoot(int speedBullet, int width,int heigth,ImageIcon bulletSprite){
        this.setBullet(new Bullet(getX()+(this.getWidth()+50)/width +6000/(width*width), getY(),width,heigth, speedBullet, bulletSprite,this));
    }
    /**
     * @return the bulletReady
     */
    public boolean isBulletReady() {
        return bulletReady;
    }

    /**
     * @param bulletReady the bulletReady to set
     */
    public final void setBulletReady(boolean bulletReady) {
        this.bulletReady = bulletReady;
    }
        /**
     * @return the bullet
     */
    public Bullet getBullet() {
        return bullet;
    }

    /**
     * @param bullet the bullet to set
     */
    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

}
