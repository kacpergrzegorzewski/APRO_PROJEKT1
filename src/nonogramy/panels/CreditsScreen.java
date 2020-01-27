package nonogramy.panels;

import nonogramy.Settings;
import nonogramy.frames.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Credits
 */
public class CreditsScreen extends JPanel {
    Image bg = new ImageIcon("img/home_background.png").getImage();

    public CreditsScreen() throws IOException {
        setLayout(null);

        JLabel authors = new JLabel("Authors");
        JLabel k = new JLabel("Kacper Grzegorzewski");
        JLabel m = new JLabel("Mateusz Suchocki");
        JLabel z = new JLabel("Zbigniew Wardaszka");

        authors.setBounds(270, 320, 500, 70);
        k.setBounds(150, 410, 500, 70);
        m.setBounds(150, 500, 500, 70);
        z.setBounds(150, 590, 500, 70);

        authors.setFont(Settings.boldFont);
        k.setFont(Settings.normalFont);
        m.setFont(Settings.normalFont);
        z.setFont(Settings.normalFont);

        k.setHorizontalAlignment(SwingConstants.CENTER);
        m.setHorizontalAlignment(SwingConstants.CENTER);
        z.setHorizontalAlignment(SwingConstants.CENTER);

        BufferedImage backButtonIcon = ImageIO.read(new File("img/button_back.png"));
        JButton back = new JButton(new ImageIcon(backButtonIcon));
        back.setBounds(870, 780, 287, 107);
        back.setBorder(BorderFactory.createEmptyBorder());
        back.setContentAreaFilled(false);

        back.addActionListener(e -> MainFrame.cl.show(MainFrame.navigation, "HOMESCREEN"));

        add(authors);
        add(k);
        add(m);
        add(z);
        add(back);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}
