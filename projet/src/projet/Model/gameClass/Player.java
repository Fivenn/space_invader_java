/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Model.gameClass;

/**
 *
 * @author mallou
 */

/* Cette classe est la classe Joueur qui gère l'entité utilisateur.
    - Elle possède un attribut points qui contient le nombre de points que le joueur possède,
        * Un attribut lifePoints pour gérer les points de vie
        * un pseudo permettant au joueur de choisir comment il veut s'appeler. 
*/
public class Player {
    private int points;
    private int lifePoints;
    private String pseudo;

    public Player(int points, int lifePoints, String pseudo) {
        this.points = points;
        this.lifePoints = lifePoints;
        this.pseudo = pseudo;
    }
    
    public void removeLifePoints(int lifePoints){
        this.lifePoints -= lifePoints;
    }

    public void addPoints(int points) {
        this.points += points;
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

    /**
     * @return the pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * @param pseudo the pseudo to set
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    
}
