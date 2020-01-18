package nonogramy.panels.play;

import nonogramy.Settings;
import nonogramy.entity.Block;
import nonogramy.entity.Board;
import nonogramy.frames.MainFrame;
import nonogramy.entity.Number;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Klasa z ekranem gry
 */
public class PlayScreen extends JPanel {

    public PlayScreen() {
        int x=100;
        int y=100;
        Block[][] block = new Block[Settings.getFieldSize()][Settings.getFieldSize()];
        Board board = new Board(Settings.getFieldSize());
        board.setTiles(Settings.getTiles());
        board.generateNumbers();
        ArrayList<ArrayList<Integer>> columnNumbers = board.getColsNumbers();
        ArrayList<ArrayList<Integer>> rowNumbers = board.getRowNumbers();

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
        JPanel columnNumbersContainer = new JPanel();
        JPanel rowNumbersContainer = new JPanel();
        Number testNumber = new Number("2");

        //Grids
        GridLayout fieldLayout = new GridLayout(Settings.getFieldSize(), Settings.getFieldSize());
        GridBagLayout containerLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        GridBagLayout columnNumbersLayout = new GridBagLayout();
        GridBagConstraints columnNumbersLayoutSettings = new GridBagConstraints();

        GridBagLayout rowNumbersLayout = new GridBagLayout();
        GridBagConstraints rowNumbersLayoutSettings = new GridBagConstraints();
        
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
                notification.setText("NONOGRAM ROZWIAZANY PRAWIDLOWO. GRATULACJE!");
                notification.repaint();
            }
            else {
                notification.setText("LOL. ZLE. XDDDDDDDD.");
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
        Tworzenie siatki liczb kolumn i rzędów
         */
        columnNumbersContainer.setLayout(columnNumbersLayout);
        int columnNumbersGridX=0;
        int columnNumbersGridY=Settings.getFieldSize();

        for (int column = 0; column < Settings.getFieldSize(); column++) {
            if(columnNumbers.size()!=0 && columnNumbers.get(column).size()==0) {
                columnNumbersLayoutSettings.gridx = columnNumbersGridX;
                columnNumbersLayoutSettings.gridy = columnNumbersGridY;
                columnNumbersContainer.add(new Number("0"), columnNumbersLayoutSettings);
                columnNumbersGridX++;
            }
            if(columnNumbers.size() > 0) {
                for (int number = columnNumbers.get(column).size()-1; number >= 0; number--) {
                    columnNumbersLayoutSettings.gridx = columnNumbersGridX;
                    columnNumbersLayoutSettings.gridy = columnNumbersGridY;
                    columnNumbersContainer.add(new Number(columnNumbers.get(column).get(number).toString()), columnNumbersLayoutSettings);
                    columnNumbersGridY--;
                }
                columnNumbersGridY = Settings.getFieldSize();
                columnNumbersGridX++;
            }
        }

        rowNumbersContainer.setLayout(rowNumbersLayout);
        int rowNumbersGridX=Settings.getFieldSize();
        int rowNumbersGridY=0;

        for (int row = 0; row < Settings.getFieldSize(); row++) {
            if(rowNumbers.size()!=0 && rowNumbers.get(row).size()==0) {
                rowNumbersLayoutSettings.gridx = rowNumbersGridX;
                rowNumbersLayoutSettings.gridy = rowNumbersGridY;
                rowNumbersContainer.add(new Number("0"), rowNumbersLayoutSettings);
                rowNumbersGridX++;
            }
            if(rowNumbers.size() > 0) {
                for (int number = rowNumbers.get(row).size()-1; number >= 0; number--) {
                    rowNumbersLayoutSettings.gridx = rowNumbersGridX;
                    rowNumbersLayoutSettings.gridy = rowNumbersGridY;
                    rowNumbersContainer.add(new Number(rowNumbers.get(row).get(number).toString()), rowNumbersLayoutSettings);
                    rowNumbersGridX--;
                }
                rowNumbersGridX = Settings.getFieldSize();
                rowNumbersGridY++;
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

        c.gridx=1;
        c.gridy=1;
        container.add(columnNumbersContainer,c);

        c.gridx=0;
        c.gridy=2;
        container.add(rowNumbersContainer,c);

        c.gridx=1;
        c.gridy=2;
        c.gridwidth=1;
        c.ipady=20;
        container.add(fieldContainer, c);

        c.gridx=0;
        c.gridy=3;
        c.gridwidth=1;
        c.weightx=0.5;
        c.anchor=GridBagConstraints.CENTER;
        container.add(solve, c);

        c.gridx=1;
        c.gridy=3;
        c.weightx=0.5;
        c.anchor=GridBagConstraints.CENTER;
        container.add(check, c);

        c.gridx=0;
        c.gridy=4;
        c.gridwidth=3;
        c.weightx=2;
        c.anchor=GridBagConstraints.CENTER;
        container.add(notification, c);

        add(container);

    }
}
