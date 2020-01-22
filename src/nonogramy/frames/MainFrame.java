package nonogramy.frames;
import nonogramy.Settings;
import nonogramy.panels.HomeScreen;
import nonogramy.panels.create.CreateScreen;
import nonogramy.panels.play.PlayScreen;
import nonogramy.panels.play.SelectSizeScreen;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Klasa odpowiedzialna za wyswietlanie okienka aplikacji. Napisana jest w niej rowniez nawigacja aplikacji
 */
public class MainFrame extends JFrame {
    private final int WIDTH = 100;
    private final int HEIGHT = WIDTH / 5 * 4;
    private final int SCALE = 12;


    public static CardLayout cl = new CardLayout(){
        @Override
        public void show(java.awt.Container parent, String name) {
            navigation.removeAll();

            JPanel homeScreen = null;
            try {
                homeScreen = new HomeScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);

        //homeButton.

        navigation.setLayout(cl);

        //tutaj chciałem dodać przycisk dostępny na wszystkich ekranach, który by nas przenosił do ekranu głównego
        home.add(homeButton);

        //domyślny ekran wyświetlany po włączeniu aplikacji
        cl.show(navigation, "HOMESCREEN");

        add(home);
        add(navigation);

        setResizable(false); //Brak resizowania
        setTitle("Nonogramy"); //Tytuł okienka
        setPreferredSize(size); //Rozmiar
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //Na środku ekranu
        setVisible(true);
    }
}

