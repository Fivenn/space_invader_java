package projet.View;

import javax.swing.*;
import java.awt.*;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

public class InformationAreaView extends JPanel {
    public InformationAreaView() {
        
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(230, 900));

        this.setLayout(new BorderLayout());
        
        JPanel panelHelpAndQuit = new JPanel(new GridLayout(1,4));
        panelHelpAndQuit.setBackground(Color.black);
        JButton newGameButton = new JButton("New Game");
        newGameButton.setPreferredSize(new Dimension(225, 100));
        JButton pauseButton = new JButton("II");
        
        
        JButton quitButton = new JButton("X");
        quitButton.setPreferredSize(new Dimension(50,50));
        JButton helpButton = new JButton("?");
        helpButton.setSize(50,50);
        
        newGameButton.setBackground(Color.black);
        newGameButton.setForeground(Color.white);
        
        pauseButton.setBackground(Color.black);
        pauseButton.setForeground(Color.white);        
        
        helpButton.setBackground(Color.black);
        helpButton.setForeground(Color.white);      

        quitButton.setBackground(Color.black);
        quitButton.setForeground(Color.red);              
        
        panelHelpAndQuit.add(pauseButton);
        panelHelpAndQuit.add(new JLabel());
        panelHelpAndQuit.add(helpButton);
        panelHelpAndQuit.add(quitButton);
        
        
        this.add(panelHelpAndQuit,NORTH);
        this.add(new LeaderBoardView(),CENTER);
        
        this.add(newGameButton,SOUTH);
        


    }
}
