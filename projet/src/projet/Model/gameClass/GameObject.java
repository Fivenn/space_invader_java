package projet.Model.gameClass;

import java.awt.Image;
import javax.swing.ImageIcon;
/* Cette classe est la classe permettant de gérer tous les objets visibles pour l'utilisateurs
    - Elle possède un attribut sprite qui gère l'image de l'objet
    - les attributs x,y,width et height permettent de définir la position de l'objet ainsi que sa taille.
    - loadImage renvoie une image à partir du sprite, elle est appelée par les fonctions draw dans le playgroundArea
    - la classe possède deux constructeurs afin de ne pas avoir à définir la taille ou la hauteur dans le constructeur de chaque classe.
*/
public abstract class GameObject {
    private ImageIcon sprite;
    private double x;
    private double y;
    double width;
    double height;
    
    public GameObject(double x, double y,ImageIcon sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public GameObject(ImageIcon sprite, double x, double y, double width, double heigth) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = heigth;
    }
    

    public Image loadImage(){
        return this.sprite.getImage();
    }
    
    public void setSprite(ImageIcon sprite){
        this.sprite = sprite;
    }
    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }
}
