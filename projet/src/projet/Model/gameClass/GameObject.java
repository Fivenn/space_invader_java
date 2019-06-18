package projet.Model.gameClass;

import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class GameObject {
    private ImageIcon sprite;
    private double x;
    private double y;
    
    public GameObject(double x, double y,ImageIcon sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
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
}
