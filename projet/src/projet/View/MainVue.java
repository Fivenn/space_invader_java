/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.View;

import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author mallou
 */
public class MainVue extends JFrame{

    public MainVue() throws HeadlessException {
        this.setTitle("Convertisseur");
        this.setSize(550, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.createWindows();
        
        this.setVisible(true);
    }
   
    
    private void createWindows(){
        
    }
}
