package nonogramy.panels.play;

import nonogramy.Settings;
import nonogramy.entity.Block;
import nonogramy.entity.Board;
import nonogramy.entity.Tile;
import nonogramy.frames.MainFrame;
import nonogramy.io.Output;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class PlayScreen extends JPanel {

    public PlayScreen() {
        int x=100;
        int y=100;
        Block[][] block = new Block[Settings.getFieldSize()][Settings.getFieldSize()];

        //Labels
        JLabel text = new JLabel("poziom trudności: " + Settings.getFieldSize());
        JLabel notification = new JLabel("");

        //Buttons
        JButton homeButton = new JButton("Powrót");
        JButton solve = new JButton("Rozwiąż nonogram");
        JButton check = new JButton("Sprawdź");

        //JPanels
        JPanel container = new JPanel();
        JPanel fieldContainer = new JPanel();

        //Grids
        GridLayout fieldLayout = new GridLayout(Settings.getFieldSize(), Settings.getFieldSize());
        GridBagLayout containerLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        
        //przypisywanie akcji do buttonów
        homeButton.addActionListener(e -> MainFrame.cl.show(MainFrame.navigation, "HOMESCREEN"));

        solve.addActionListener(e -> {

        });

        check.addActionListener(e -> {
            int i=0;
            boolean isCorrect = true;
            boolean correctAnswer;
            boolean currentAnswer;
            for(int row=0; row<Settings.getFieldSize(); row++ ) {
                for (int column = 0; column < Settings.getFieldSize(); column++) {
                    correctAnswer = Settings.getTiles()[i].isChecked();
                    currentAnswer = block[row][column].getBlock();
                    if(correctAnswer != currentAnswer) {
                        isCorrect = false;
                        break;
                    }
                    i++;
                }
                if(!isCorrect) break;
            }

            if (isCorrect) {
                notification.setText("NONOGRAM ROZWIĄZANY PRAWIDŁOWO. GRATULACJE!");
                notification.repaint();
            }
            else {
                notification.setText("LOL. ŹLE. XDDDDDDDD.");
                notification.repaint();
            }
        });

        /*
          Tworzenie siatki nonogramu
         */
        for(int row=0; row<Settings.getFieldSize(); row++ ) {
            for(int column=0; column<Settings.getFieldSize(); column++ ) {
                block[row][column] = new Block();
                int finalRow = row;
                int finalColumn = column;
                block[row][column].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        block[finalRow][finalColumn].click();
                        block[finalRow][finalColumn].repaint();
                    }
                });
                fieldContainer.add(block[row][column]);
            }
        }

        /*
          Ustawianie wyglądu wszystkiego
         */
        fieldContainer.setLayout(fieldLayout);
        container.setLayout(containerLayout);

        setBackground(Color.WHITE);
        container.setBackground(Color.WHITE);
        fieldContainer.setBackground(Color.WHITE);

        c.gridx=0;
        c.gridy=0;
        container.add(homeButton, c);

        c.gridx=1;
        c.gridy=0;
        container.add(text, c);

        c.gridx=0;
        c.gridy=1;
        c.gridwidth=2;
        c.ipady=20;
        container.add(fieldContainer, c);

        c.gridx=0;
        c.gridy=2;
        c.gridwidth=1;
        c.weightx=0.5;
        c.anchor=GridBagConstraints.CENTER;
        container.add(solve, c);

        c.gridx=1;
        c.gridy=2;
        c.weightx=0.5;
        c.anchor=GridBagConstraints.CENTER;
        container.add(check, c);

        c.gridx=0;
        c.gridy=3;
        c.gridwidth=1;
        c.weightx=2;
        c.anchor=GridBagConstraints.CENTER;
        container.add(notification, c);

        add(container);

    }
}
