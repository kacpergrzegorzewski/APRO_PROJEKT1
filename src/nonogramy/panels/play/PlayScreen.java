package nonogramy.panels.play;

import nonogramy.Settings;
import nonogramy.frames.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayScreen extends JPanel {


    //przycisk do cofania na stronę główną, żeby nie było trzeba przy testowaniu za każdym razem reruna robić.
    private JButton homeButton = new JButton("home");


    public PlayScreen() {
        JLabel text = new JLabel("poziom trudności: " + Settings.getFieldSize());

        homeButton.addActionListener(e -> MainFrame.cl.show(MainFrame.navigation, "HOMESCREEN"));

        add(homeButton);
        add(text);
    }
}
