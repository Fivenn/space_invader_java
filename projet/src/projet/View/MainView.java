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
public class MainView extends JFrame{

    public MainView() throws HeadlessException {
        add(new PlaygroundArea());

        setResizable(true);
        pack();

        setTitle("Space Invaders");
        setSize(1100, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }
}
