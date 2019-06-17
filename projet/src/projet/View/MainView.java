/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.View;

import java.awt.*;
import javax.swing.JFrame;

/**
 *
 * @author mallou
 */
public class MainView extends JFrame{

    public MainView() throws HeadlessException {
        InformationAreaView informationAreaView = new InformationAreaView();
        PlaygroundAreaView playgroundAreaView = new PlaygroundAreaView();

        
        
        this.setLayout(new BorderLayout());
        
        this.add(playgroundAreaView, BorderLayout.WEST);
        this.add(informationAreaView, BorderLayout.EAST);

        setResizable(true);
        pack();

        setTitle("Space Invaders");
        setSize(1150, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }
}
