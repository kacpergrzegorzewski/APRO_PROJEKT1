package nonogramy.panels.create;

import nonogramy.Settings;
import nonogramy.frames.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Ekran słuzacy do wyboru poziomu trudnosci
 */
public class SelectSizeScreen extends JPanel {
    Image bg = new ImageIcon("img/select_background.png").getImage();

    public SelectSizeScreen() throws IOException {
        setLayout(null);

        BufferedImage easyButtonIcon = ImageIO.read(new File("img/button_easy.png"));
        BufferedImage mediumButtonIcon = ImageIO.read(new File("img/button_medium.png"));
        BufferedImage hardButtonIcon = ImageIO.read(new File("img/button_hard.png"));
        BufferedImage backButtonIcon = ImageIO.read(new File("img/button_back.png"));

        JButton easy = new JButton(new ImageIcon(easyButtonIcon));
        easy.setBounds(260, 280, 357, 137);
        easy.setBorder(BorderFactory.createEmptyBorder());
        easy.setContentAreaFilled(false);

        JButton medium = new JButton(new ImageIcon(mediumButtonIcon));
        medium.setBounds(260, 450, 357, 137);
        medium.setBorder(BorderFactory.createEmptyBorder());
        medium.setContentAreaFilled(false);

        JButton hard = new JButton(new ImageIcon(hardButtonIcon));
        hard.setBounds(260, 620, 357, 137);
        hard.setBorder(BorderFactory.createEmptyBorder());
        hard.setContentAreaFilled(false);

        JButton back = new JButton(new ImageIcon(backButtonIcon));
        back.setBounds(870, 780, 287, 107);
        back.setBorder(BorderFactory.createEmptyBorder());
        back.setContentAreaFilled(false);

        easy.addActionListener(e -> {
            Settings.setFieldSize(5);
            MainFrame.cl.show(MainFrame.navigation, "CREATESCREEN");
        });

        medium.addActionListener(e -> {
            Settings.setFieldSize(10);
            MainFrame.cl.show(MainFrame.navigation, "CREATESCREEN");
        });

        hard.addActionListener(e -> {
            Settings.setFieldSize(15);
            MainFrame.cl.show(MainFrame.navigation, "CREATESCREEN");
        });

        back.addActionListener(e -> MainFrame.cl.show(MainFrame.navigation, "HOMESCREEN"));

        add(easy);
        add(medium);
        add(hard);
        add(back);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}
