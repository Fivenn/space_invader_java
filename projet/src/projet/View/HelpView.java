package projet.View;

import projet.Controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class HelpView extends JFrame {
    private final GameController gameController;
    private final InformationAreaView infoView;
    
    public HelpView(GameController gameController,InformationAreaView infoView) {
        this.gameController = gameController;
        this.infoView = infoView;

        this.setTitle("Help and Options");
        this.setSize(300, 700);
        this.setLayout(new BorderLayout());
        
        this.setLocationRelativeTo(this.infoView);
        
        
        JLabel helpLabel = new JLabel("Help and Options");
        helpLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton backButton = new JButton("Close");


        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onBack();
            }
        });

        this.add(helpLabel,BorderLayout.NORTH);
        this.add(createPanelCustom(),BorderLayout.CENTER);
        this.add(backButton,BorderLayout.SOUTH);
        
        
        this.setResizable(false);
        this.pack();
    }
    private void onBack(){
        this.gameController.pauseGame();
        this.setVisible(false);
        this.requestFocus(true);
    }
    private JPanel createPanelCustom(){
        JPanel p = new JPanel(new GridLayout(1,2));
        p.add(createPanelCustomSprite());
        p.add(createPanelCustomVariables());
        return p;
    }
    
    private JPanel createPanelCustomVariables(){
        JPanel p = new JPanel(new GridLayout(1,2));
        JPanel customOrNot = new JPanel(new BorderLayout());
        JButton sunButton = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("sun.png")));
        sunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gameController.setBackgroundImage(new ImageIcon(this.getClass().getClassLoader().getResource("background.png")).getImage());
            }
        });
        sunButton.setBackground(new Color(25,142,232));
        
        JButton moonButton = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("moon.png")));
        moonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gameController.setBackgroundImage(new ImageIcon(this.getClass().getClassLoader().getResource("backgroundNight.png")).getImage());
            }
        });
        moonButton.setBackground(new Color(12,71,116));
        
        changeBackgroundPanel.add(sunButton,BorderLayout.WEST);
        changeBackgroundPanel.add(moonButton,BorderLayout.EAST);     
        p.add(changeBackgroundPanel);
        
        return p;
    }
    
    private JPanel createPanelCustomSprite(){
        JPanel p = new JPanel(new GridLayout(4,1));
        
        JPanel changeBackgroundPanel = new JPanel(new BorderLayout());
        JButton sunButton = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("sun.png")));
        sunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gameController.setBackgroundImage(new ImageIcon(this.getClass().getClassLoader().getResource("background.png")).getImage());
            }
        });
        sunButton.setBackground(new Color(25,142,232));
        
        JButton moonButton = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("moon.png")));
        moonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gameController.setBackgroundImage(new ImageIcon(this.getClass().getClassLoader().getResource("backgroundNight.png")).getImage());
            }
        });
        moonButton.setBackground(new Color(12,71,116));
        
        changeBackgroundPanel.add(sunButton,BorderLayout.WEST);
        changeBackgroundPanel.add(moonButton,BorderLayout.EAST);     
        p.add(changeBackgroundPanel);
        
        Dimension prefSize = new Dimension(100, 60);
        
        JPanel changeSpaceShipPanel = new JPanel(new BorderLayout());
        
        JButton chickenButton = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("spaceshipPiou.png")));
        chickenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gameController.getSpaceShip().setSprite(new ImageIcon(this.getClass().getClassLoader().getResource("spaceshipPiou.png")));
                gameController.getSpaceShip().setWidth(60);
                gameController.getSpaceShip().setHeigth(80);
                gameController.getSpaceShip().setY(580);
                gameController.notifier();
            }
        });
        chickenButton.setBackground(Color.BLACK);
        chickenButton.setPreferredSize(prefSize);
        
        JButton shipButton = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("ship.gif")));
        shipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gameController.getSpaceShip().setSprite(new ImageIcon(this.getClass().getClassLoader().getResource("ship.gif")));
                gameController.getSpaceShip().setWidth(40);
                gameController.getSpaceShip().setHeigth(40);
                gameController.getSpaceShip().setY(600);
                gameController.notifier();
            }
        });
        shipButton.setBackground(Color.BLACK);       
        shipButton.setPreferredSize(prefSize);
        
        changeSpaceShipPanel.add(chickenButton,BorderLayout.WEST);
        changeSpaceShipPanel.add(shipButton,BorderLayout.EAST);     
        p.add(changeSpaceShipPanel);
        
        
        JPanel changeAliensPanel = new JPanel(new BorderLayout());
        
        JButton foxButton = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("fox.png")));
        foxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gameController.setAliensSprite(new ImageIcon(this.getClass().getClassLoader().getResource("fox.png")));
            }
        });
        foxButton.setBackground(Color.BLACK);    
        foxButton.setPreferredSize(prefSize);
        
        JButton alienButton = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("alien.gif")));
        alienButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gameController.setAliensSprite(new ImageIcon(this.getClass().getClassLoader().getResource("alien.gif")));
            }
        });
        alienButton.setBackground(Color.BLACK);
        alienButton.setPreferredSize(prefSize);
           
        
        changeAliensPanel.add(foxButton,BorderLayout.WEST);
        changeAliensPanel.add(alienButton,BorderLayout.EAST);     
        p.add(changeAliensPanel);
        
        
        
        JPanel changeMissilePanel = new JPanel(new BorderLayout());
        
        JButton eggButton = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("egg.png")));
        eggButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gameController.setBulletSprite(new ImageIcon(this.getClass().getClassLoader().getResource("egg.png")),50,50); 
            }
            
        });
        eggButton.setBackground(Color.BLACK);    
        eggButton.setPreferredSize(prefSize);
        
        JButton missileButton = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("shot.gif")));
        missileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gameController.setBulletSprite(new ImageIcon(this.getClass().getClassLoader().getResource("shot.gif")),20,30);
            }
        });
        missileButton.setBackground(Color.BLACK);
        missileButton.setPreferredSize(prefSize);
           
        
        changeMissilePanel.add(eggButton,BorderLayout.WEST);
        changeMissilePanel.add(missileButton,BorderLayout.EAST);     
        p.add(changeMissilePanel);
        
        
        
        return p;
    }
    
   
   
}
