package projet.View;

import projet.Controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        this.setBackground(Color.BLACK);
        
        
        JLabel helpLabel = new JLabel("Help and Options");
        
        JButton backButton = new JButton("Close");

        helpLabel.setForeground(Color.WHITE);

        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onBack();
            }
        });

        this.add(helpLabel,BorderLayout.NORTH);
        this.add(backButton,BorderLayout.SOUTH);
        
        
        this.setResizable(false);
        this.pack();
    }
    private void onBack(){
        this.gameController.pauseGame();
        this.setVisible(false);
        this.requestFocus(true);
    }
}
