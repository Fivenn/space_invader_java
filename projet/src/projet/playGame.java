/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import projet.Controller.GameController;
import projet.View.MainVue;

/**
 *
 * @author mallou
 */
public class playGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GameController gc = new GameController();
        MainVue fenetre = new MainVue();
    }
    
}
