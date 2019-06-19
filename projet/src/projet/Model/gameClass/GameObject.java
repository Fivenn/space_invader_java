package projet.Model.gameClass;

import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class GameObject {
    private ImageIcon sprite;
    private double x;
    private double y;
    private double width;
    private double heigth;
    
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
        this.heigth = heigth;
    }
    

    public Image loadImage(){
        return this.sprite.getImage();
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
     * @return the heigth
     */
    public double getHeigth() {
        return heigth;
    }

    /**
     * @param heigth the heigth to set
     */
    public void setHeigth(double heigth) {
        this.heigth = heigth;
    }
}
