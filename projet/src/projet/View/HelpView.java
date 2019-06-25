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
    private final GameController gameController; // Contrôleur de notre jeu
    private final InformationAreaView infoView; // Vue représentant la zone d'information à la droite du plateau de jeu

    /* classe définissant la vue d'aide et de configuration du jeu */
    public HelpView(GameController gameController,InformationAreaView infoView) {

        /* Initialisation des variables et utiles à cette vue */
        this.gameController = gameController;
        this.infoView = infoView;
        String rules = "<html><h1>Règle du jeu</h1>" +
                "<p align='justify'>   Piou-piou est un shoot'em up dans lequel vous contrôlez un poulet possédant 3 points de vie et pouvant tirer des oeufs. Les spacefox descendent dans la zone et rapportent 10 points. Les vaisseaux spacefox rapportent 100 points et possèdent 3 points de vie. Lorsque tous les spacefox sont éliminés, une nouvelle vague apparaït. Lorsque le poulet meurt ou que les spacefox atteignent le bas de l'écran, ils ont réussi l'invasion et vous perdez alors la partie.<br><br></p>" +
                "<p align='justify'>    Appuyez sur Espace pour tirer. Utilisez les flèches de gauche et de droite pour diriger le poulet. Appuyez sur Nouvelle partie pour rejouer. Appuyez sur P pour mettre en pause.</p><" +
                "<h1>Personnalisation</h1></html>";
        this.setTitle("Help and Options");
        this.setSize(300, 700);
        this.setLayout(new BorderLayout());
        
        this.setLocationRelativeTo(this.infoView);

        /* Création et configuration des objets graphiques */
        JLabel helpLabel = new JLabel(rules);
        helpLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        helpLabel.setPreferredSize(new Dimension(290, 350));
        helpLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton backButton = new JButton("Close");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        
        /* Déclaration de l'action à réaliser lors de l'appui sur le bouton */
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onBack(); // Ferme la fenêtre d'aide et de configuration puis reprise du jeu
            }
        });

        /* Ajout des éléments graphiques à la fenêtre d'aide et de configuration */
        this.add(helpLabel,BorderLayout.NORTH);
        this.add(createPanelCustom(),BorderLayout.CENTER);
        this.add(backButton,BorderLayout.SOUTH);

        this.setResizable(false);
        this.pack();
    }
    /* Fonction permettant de fermer la fenêtre d'aide et de configuration puis reprise du jeu */
    private void onBack(){
        this.gameController.pauseGame();
        this.setVisible(false);
        this.requestFocus(true);
    }

    /* Fonction permettant la création d'un Panel avec les éléments de configuration possibles */
    private JPanel createPanelCustom(){
        JPanel p = new JPanel(new BorderLayout());
        p.add(createPanelCustomSprite(),BorderLayout.NORTH);
        p.add(createPanelCustomVariables(),BorderLayout.SOUTH);
        return p;
    }

    /* Fonction permettant de modifier la valeur des variables jeu */
    private JPanel createPanelCustomVariables(){
        JPanel p = new JPanel(new BorderLayout());
        
        JCheckBox customCheck = new JCheckBox("Keep Changes between levels?");

        /* On change l'état de la variable custom du contrôleur quand la box est cochée */
        customCheck.addItemListener((ItemEvent arg0) -> {
            gameController.setCustom(arg0.getStateChange()==1);
        });
        
        p.add(customCheck,BorderLayout.NORTH);
        
        JPanel customVariables = new JPanel(new GridLayout(5,2));
        customVariables.add(new JLabel("Aliens fire rate : "));
        
        JTextField aliensFireRate = new JTextField(String.valueOf(gameController.getNbChancesBulletAlien()));
        /* Écoute et modifie la valeur du rate des missiles des aliens */
        aliensFireRate.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent arg0) {}

            @Override
            public void keyPressed(KeyEvent arg0) {}

            @Override
            public void keyReleased(KeyEvent arg0) {
                try{
                    int a = Integer.parseInt(aliensFireRate.getText());
                    
                    gameController.setNbChancesBulletAlien(a); // Modifie le rate des missiles des aliens
                    aliensFireRate.setForeground(Color.black);
                }catch(NumberFormatException e){
                    /* Dans le cas où la valeur n'est pas valide, on met le rate à 5000 par défaut et colore le texte saisi en rouge */
                    gameController.setNbChancesBulletAlien(5000);
                    aliensFireRate.setForeground(Color.red);
                }
            }
        });
        customVariables.add(aliensFireRate);
        customVariables.add(new JLabel("Aliens per rows : (10 max)"));

        /* Écoute et modifie le nombre d'aliens par ligne */
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
                    /* Si la valeur saisie est supérieure à 10, on modifie le nombre d'aliens à 10 tout en modifiant le champ de texte */
                    if(a>10){
                        gameController.setNbAliensLigne(10);
                        aliensPerRows.setText("10");
                    }else{
                        gameController.setNbAliensLigne(a);
                    }
                    /* Dans le cas où il y a une erreur, on set le nombre d'aliens à 2 */
                    aliensPerRows.setForeground(Color.black);
                }catch(NumberFormatException e){
                    gameController.setNbAliensLigne(2);
                    aliensPerRows.setForeground(Color.red);
                }
            }
        });
        customVariables.add(aliensPerRows);
        customVariables.add(new JLabel("Aliens per column : (10 max)"));

        /* Écoute et modifie le nombre d'aliens par colonne */
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
                    /* Si la valeur saisie est supérieure à 10, on modifie le nombre d'aliens à 10 tout en modifiant le champ de texte */
                    if(a>10){
                        gameController.setNbAliensColonnes(10);
                        aliensPerColumn.setText("10");
                    }else{
                        gameController.setNbAliensColonnes(a);
                    }
                    /* Dans le cas où il y a une erreur, on met le nombre d'aliens à 2 */
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
        /* Écoute et modifie la vitesse des aliens */
        aliensSpeed.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent arg0) {}

            @Override
            public void keyPressed(KeyEvent arg0) {}

            @Override
            public void keyReleased(KeyEvent arg0) {
                try{
                    int a = Integer.parseInt(aliensSpeed.getText());
                    gameController.setAlienSpeed(a); //Modifie la vitesse en fonction de la saisie du champ
                    aliensSpeed.setForeground(Color.black);
                }catch(NumberFormatException e){
                    /* Dans le cas où il y a une erreur, on met la vitesse à 1 */
                    gameController.setAlienSpeed(1);
                    aliensSpeed.setForeground(Color.red);
                }
            }
        });

        customVariables.add(aliensSpeed);
        customVariables.add(new JLabel("Pseudo : "));

        JTextField pseudo = new JTextField(String.valueOf(gameController.getPlayer().getPseudo()));
        /* Écoute et modifie le pseudo */
        pseudo.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent arg0) {}

            @Override
            public void keyPressed(KeyEvent arg0) {}

            @Override
            public void keyReleased(KeyEvent arg0) {
                gameController.setPseudo(pseudo.getText());
            }
        });

        customVariables.add(pseudo);
        p.add(customVariables,BorderLayout.CENTER);


        return p;
    }

    /* Fonction permettant la création d'un panel avec des sprites + définition des actions des différents éléments qu'il comporte. */
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
                gameController.getSpaceShip().setHeight(80);
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
                gameController.getSpaceShip().setHeight(40);
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
