package nonogramy.panels;

import nonogramy.frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
Pierwszy ekran, wyświetlany po włączeniu aplikacji
 */

public class HomeScreen extends JPanel {
    public HomeScreen() {
        //wymiary okienka. nie wiem czemu akurat tutaj. pozdrawiam.
        setPreferredSize(new Dimension(450, 500));

        GridLayout gl = new GridLayout(4,4);
        gl.setVgap(20);
        setLayout(gl);
        
        JButton playButton = new JButton("Graj");
        JButton createButton = new JButton("Stwórz nonogram");

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


        add(playButton);
        add(createButton);
    }
}
