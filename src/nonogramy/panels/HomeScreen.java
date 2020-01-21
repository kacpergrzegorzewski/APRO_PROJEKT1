package nonogramy.panels;

import nonogramy.Settings;
import nonogramy.frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Pierwszy ekran, wyswietlany po właczeniu aplikacji
 */
public class HomeScreen extends JPanel {
    public HomeScreen() {
        setLayout(null);
        
        JButton playButton = new JButton("Play");
        playButton.setBounds(100, 50, 400, 150);
        playButton.setFont(Settings.homeFont);

        JButton createButton = new JButton("Create nonogram");
        createButton.setBounds(100, 250, 400, 150);
        createButton.setFont(Settings.homeFont);

        JButton creditsButton = new JButton("Credits");
        creditsButton.setBounds(100, 450, 400, 150);
        creditsButton.setFont(Settings.homeFont);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(100, 650, 400, 150);
        exitButton.setFont(Settings.homeFont);

        //TODO Tło inne
        //TODO Z prawej strony obrazek nonogramu

        //tak mniej więcej wygląda implementacja zachowania buttona po wciśnięciu.
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.cl.show(MainFrame.navigation, "SELECTSIZESCREEN_play");
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.cl.show(MainFrame.navigation, "SELECTSIZESCREEN_create");
            }
        });

        creditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MainFrame.cl.show(MainFrame.navigation, "SELECTSIZESCREEN_create");
                //TODO przejście do ekranu z autorami
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(playButton);
        add(createButton);
        add(creditsButton);
        add(exitButton);
    }
}
