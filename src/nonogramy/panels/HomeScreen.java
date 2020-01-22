package nonogramy.panels;

import nonogramy.Settings;
import nonogramy.frames.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Pierwszy ekran, wyswietlany po właczeniu aplikacji
 */
public class HomeScreen extends JPanel {
    Image bg = new ImageIcon("img/home_background.png").getImage();

    public HomeScreen() throws IOException {
        setLayout(null);

        BufferedImage playButtonIcon = ImageIO.read(new File("img/button_play.png"));
        BufferedImage createButtonIcon = ImageIO.read(new File("img/button_create.png"));
        BufferedImage creditsButtonIcon = ImageIO.read(new File("img/button_credits.png"));
        BufferedImage exitButtonIcon = ImageIO.read(new File("img/button_exit.png"));

        JButton playButton = new JButton(new ImageIcon(playButtonIcon));
        playButton.setBounds(260, 240, 357, 137);
        playButton.setBorder(BorderFactory.createEmptyBorder());
        playButton.setContentAreaFilled(false);

        JButton createButton = new JButton(new ImageIcon(createButtonIcon));
        createButton.setBounds(260, 400, 357, 137);
        createButton.setBorder(BorderFactory.createEmptyBorder());
        createButton.setContentAreaFilled(false);

        JButton creditsButton = new JButton(new ImageIcon(creditsButtonIcon));
        creditsButton.setBounds(260, 560, 357, 137);
        creditsButton.setBorder(BorderFactory.createEmptyBorder());
        creditsButton.setContentAreaFilled(false);

        JButton exitButton = new JButton(new ImageIcon(exitButtonIcon));
        exitButton.setBounds(260, 720, 357, 137);
        exitButton.setBorder(BorderFactory.createEmptyBorder());
        exitButton.setContentAreaFilled(false);

        //tak mniej więcej wygląda implementacja zachowania buttona po wciśnięciu.
        playButton.addActionListener(e -> MainFrame.cl.show(MainFrame.navigation, "SELECTSIZESCREEN_play"));

        createButton.addActionListener(e -> MainFrame.cl.show(MainFrame.navigation, "SELECTSIZESCREEN_create"));

        creditsButton.addActionListener(e -> {
            //MainFrame.cl.show(MainFrame.navigation, "SELECTSIZESCREEN_create");
            //TODO przejście do ekranu z autorami
        });

        exitButton.addActionListener(e -> System.exit(0));

        add(playButton);
        add(createButton);
        add(creditsButton);
        add(exitButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}
