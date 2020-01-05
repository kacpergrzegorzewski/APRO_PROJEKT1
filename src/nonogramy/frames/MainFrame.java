package nonogramy.frames;
import nonogramy.Settings;
import nonogramy.panels.HomeScreen;
import nonogramy.panels.create.CreateScreen;
import nonogramy.panels.play.PlayScreen;
import nonogramy.panels.play.SelectSizeScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
Klasa odpowiedzialna za wyświetlanie okienka aplikacji. Napisana jest w niej również nawigacja aplikacji.
 */


public class MainFrame extends JFrame {

    public static CardLayout cl = new CardLayout(){
        @Override
        public void show(java.awt.Container parent, String name) {
            navigation.removeAll();

            JPanel homeScreen = new HomeScreen();
            JPanel selectSizeScreen_play = new SelectSizeScreen();
            JPanel playScreen = new PlayScreen();
            JPanel selectSizeScreen_create = new nonogramy.panels.create.SelectSizeScreen();
            JPanel create = new CreateScreen();

            // schemat dodawania kolejnych ekranów - navigation.add([JPanel klasy ekranu utworzony jak wyżej] , [nazwa jakiej chcemy używać przy definiowaniu przycisku odpowiedzialnego za przechodzenie do danego ekranu]
            navigation.add(homeScreen, "HOMESCREEN");
            navigation.add(selectSizeScreen_play, "SELECTSIZESCREEN_play");
            navigation.add(playScreen, "PLAYSCREEN");
            navigation.add(selectSizeScreen_create, "SELECTSIZESCREEN_create");
            navigation.add(create, "CREATESCREEN");

            super.show(parent, name);
            navigation.revalidate();
            navigation.repaint();
        }
    };
    public static JPanel navigation = new JPanel();
    private static JPanel home = new JPanel();
    private JButton homeButton = new JButton("home");

    public MainFrame() {
        //nazwa okienka
        super("Nonogramy");
        navigation.setLayout(cl);

        //tutaj chciałem dodać przycisk dostępny na wszystkich ekranach, który by nas przenosił do ekranu głównego
        home.add(homeButton);

        //domyślny ekran wyświetlany po włączeniu aplikacji
        cl.show(navigation, "HOMESCREEN");

        add(home);
        add(navigation);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

