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

/* Cette classe est la classe Vaisseau. Elle définit des objets visibles, déplacables pouvant tirer.
    - La fonction shoot redéfinit celle que touchableObject et permet de tirer en définissant les attributs de la bullet.
    - La fonction possède des attributs de la classe bullet afin de pouvoir les personnaliser.
*/

public class SpaceShip extends TouchableGameObject{
    private ImageIcon bulletIcon;
    private int widthBullet;
    private int heightBullet;
    
    public SpaceShip(double x, double y, int speed, ImageIcon sprite) {
        super(x, y, speed,sprite,60,80);
        this.bulletIcon = new ImageIcon(this.getClass().getClassLoader().getResource("egg.png"));
        this.heightBullet = 50;
        this.widthBullet = 50;
    }

    
    
    public void shoot(){
        if(isBulletReady()){
            super.shoot(5, getWidthBullet(), getHeightBullet(), getBulletIcon());
            this.setBulletReady(false);
        }
    }

    /**
     * @return the bulletIcon
     */
    public ImageIcon getBulletIcon() {
        return bulletIcon;
    }

    /**
     * @param bulletIcon the bulletIcon to set
     */
    public void setBulletIcon(ImageIcon bulletIcon) {
        this.bulletIcon = bulletIcon;
    }

    /**
     * @return the widthBullet
     */
    public int getWidthBullet() {
        return widthBullet;
    }

    /**
     * @param widthBullet the widthBullet to set
     */
    public void setWidthBullet(int widthBullet) {
        this.widthBullet = widthBullet;
    }

    /**
     * @return the heightBullet
     */
    public int getHeightBullet() {
        return heightBullet;
    }

    /**
     * @param heightBullet the heightBullet to set
     */
    public void setHeightBullet(int heightBullet) {
        this.heightBullet = heightBullet;
    }

}
