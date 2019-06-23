package projet.View;

import projet.Controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
        JPanel p = new JPanel(new BorderLayout());
        p.add(createPanelCustomSprite(),BorderLayout.NORTH);
        p.add(createPanelCustomVariables(),BorderLayout.SOUTH);
        return p;
    }
    
    private JPanel createPanelCustomVariables(){
        JPanel p = new JPanel(new BorderLayout());
        
        JCheckBox customCheck = new JCheckBox("Keep Changes between levels?");
        
        customCheck.addItemListener((ItemEvent arg0) -> {
            gameController.setCustom(arg0.getStateChange()==1);
        });
        
        p.add(customCheck,BorderLayout.NORTH);
        
        JPanel customVariables = new JPanel(new GridLayout(4,2));
        customVariables.add(new JLabel("Aliens fire rate : "));
        
        JTextField aliensFireRate = new JTextField(String.valueOf(gameController.getNbChancesBulletAlien()));
        aliensFireRate.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent arg0) {}

            @Override
            public void keyPressed(KeyEvent arg0) {}

            @Override
            public void keyReleased(KeyEvent arg0) {
                try{
                    int a = Integer.parseInt(aliensFireRate.getText());
                    
                    gameController.setNbChancesBulletAlien(a);
                    aliensFireRate.setForeground(Color.black);
                }catch(NumberFormatException e){
                    gameController.setNbChancesBulletAlien(5000);
                    aliensFireRate.setForeground(Color.red);
                }
            }
        });
        customVariables.add(aliensFireRate);
        customVariables.add(new JLabel("Aliens per rows : (10 max)"));
        
        JTextField aliensPerRows = new JTextField(String.valueOf(gameController.getNbAliensLigne()));
        aliensPerRows.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent arg0) {}

            @Override
            public void keyPressed(KeyEvent arg0) {}

            @Override
            public void keyReleased(KeyEvent arg0) {
                try{
                    int a = Integer.parseInt(aliensPerRows.getText());
                    if(a>10){
                        gameController.setNbAliensLigne(10);
                        aliensPerRows.setText("10");
                    }else{
                        gameController.setNbAliensLigne(a);
                    }
                    aliensPerRows.setForeground(Color.black);
                }catch(NumberFormatException e){
                    gameController.setNbAliensLigne(2);
                    aliensPerRows.setForeground(Color.red);
                }
            }
        });
        customVariables.add(aliensPerRows);
        customVariables.add(new JLabel("Aliens per column : (10 max)"));
        
        JTextField aliensPerColumn = new JTextField(String.valueOf(gameController.getNbAliensColonnes()));
        aliensPerColumn.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent arg0) {}

            @Override
            public void keyPressed(KeyEvent arg0) {}

            @Override
            public void keyReleased(KeyEvent arg0) {
                try{
                    int a = Integer.parseInt(aliensPerColumn.getText()); 
                    if(a>10){
                        gameController.setNbAliensColonnes(10);
                        aliensPerColumn.setText("10");
                    }else{
                        gameController.setNbAliensColonnes(a);
                    }
                    aliensPerColumn.setForeground(Color.black);
                }catch(NumberFormatException e){
                    gameController.setNbAliensColonnes(2);
                    aliensPerColumn.setForeground(Color.red);
                }
            }
        });
        customVariables.add(aliensPerColumn);      
        
        customVariables.add(new JLabel("Aliens speed : "));
        
        JTextField aliensSpeed = new JTextField(String.valueOf(gameController.getAlienSpeed()));
        aliensSpeed.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent arg0) {}

            @Override
            public void keyPressed(KeyEvent arg0) {}

            @Override
            public void keyReleased(KeyEvent arg0) {
                try{
                    int a = Integer.parseInt(aliensSpeed.getText());
                    gameController.setAlienSpeed(a);
                    aliensSpeed.setForeground(Color.black);
                }catch(NumberFormatException e){
                    gameController.setAlienSpeed(1);
                    aliensSpeed.setForeground(Color.red);
                }
            }
        });
        customVariables.add(aliensSpeed);
        
        p.add(customVariables,BorderLayout.CENTER);
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
