package nonogramy.panels.create;

import nonogramy.Settings;
import nonogramy.frames.MainFrame;

import javax.swing.*;

/**
 * Ekran słuzacy do wyboru poziomu trudnosci
 */

public class SelectSizeScreen extends JPanel {
    private JButton easy = new JButton("5x5");
    private JButton medium = new JButton("10x10");
    private JButton hard = new JButton("15x15");

    public SelectSizeScreen() {
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

        add(easy);
        add(medium);
        add(hard);
    }
}
