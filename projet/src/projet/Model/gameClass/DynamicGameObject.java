/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Model.gameClass;

import javax.swing.ImageIcon;

/* Cette classe est la classe permettant de gérer tous les objets pouvant se déplacer. 
    - Elle est fille de la classe gameObject car tous les objets gérés par cette classe sont visibles.
    - Elle possède un attribut speed qui définit la vitesse de l'objet.
    - La fonction move permet de modifier les coordonnées de l'objet selon si il va à droite ou à gauche.
    - La classe possède deux constructeurs afin de ne pas avoir à définir la taille ou la hauteur dans le constructeur de chaque classe.
*/

public abstract class DynamicGameObject extends GameObject {
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
            if(getX()>0)setX(getX() - getSpeed());
        }else{
            if(getX()<850)setX(getX() + getSpeed());
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
