package nonogramy.panels.play;

import nonogramy.Settings;
import nonogramy.frames.MainFrame;

import javax.swing.*;

/**
ekran służący do wyboru poziomu trudności.
 */

public class SelectSizeScreen extends JPanel {
    private JButton easy = new JButton("5x5");
    private JButton medium = new JButton("10x10");
    private JButton hard = new JButton("15x15");


    public SelectSizeScreen() {
        easy.addActionListener(e -> {
            Settings.setBoardSize(5);
            MainFrame.cl.show(MainFrame.navigation, "PLAYSCREEN");
        });

        medium.addActionListener(e -> {
            Settings.setBoardSize(10);
            MainFrame.cl.show(MainFrame.navigation, "PLAYSCREEN");
        });

        hard.addActionListener(e -> {
            Settings.setBoardSize(15);
            MainFrame.cl.show(MainFrame.navigation, "PLAYSCREEN");
        });

        add(easy);
        add(medium);
        add(hard);
    }
}
