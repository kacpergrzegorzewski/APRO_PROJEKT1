package nonogramy.panels.create;

import javax.swing.*;

import nonogramy.Settings;
import nonogramy.entity.Block;
import nonogramy.entity.Board;
import nonogramy.entity.Tile;
import nonogramy.frames.MainFrame;
import nonogramy.io.Output;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Ekran, w ktorym uzytkownik moze sam stworzyc nonogram lub wygenerowac losowo
 */

public class CreateScreen extends JPanel {
    Image bg = new ImageIcon("img/play_background.png").getImage();

    public CreateScreen() {
        Block[][] block = new Block[Settings.getBoardSize()][Settings.getBoardSize()];

        //Labels
        JLabel text = new JLabel("Difficulty: " + Settings.getBoardSize());
        text.setFont(Settings.newFont);

        //Buttons
        JButton homeButton = new JButton("Return");
        JButton randomButton = new JButton("Generate random nonogram");
        JButton saveButton = new JButton("Save");

        //JPanels
        JPanel container = new JPanel();
        JPanel fieldContainer = new JPanel();

        //Grids
        GridLayout fieldLayout = new GridLayout(Settings.getBoardSize(), Settings.getBoardSize());
        GridBagLayout containerLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        //przypisywanie akcji do buttonów
        homeButton.addActionListener(e -> MainFrame.cl.show(MainFrame.navigation, "HOMESCREEN"));

        randomButton.addActionListener(e -> {
            int currentTile = 0;
            Board board = new Board(Settings.getBoardSize());
            board.generateRandomBoard();
            boolean[] values = board.getBoard();
            board.generateRandomBoard();
            for (int row = 0; row < Settings.getBoardSize(); row++) {
                for (int column = 0; column < Settings.getBoardSize(); column++) {
                    block[row][column].setBlock(values[currentTile++]);
                    block[row][column].repaint();
                    //System.out.println(values[row+column]);
                }
            }
        });

        saveButton.addActionListener(e -> {
            int numberOfBlocks = Settings.getBoardSize()*Settings.getBoardSize();
            int currentTile = 0;
            Board board = new Board(Settings.getBoardSize());
            Tile[] tiles = new Tile[numberOfBlocks];

            for (int row = 0; row < Settings.getBoardSize(); row++) {
                for (int column = 0; column < Settings.getBoardSize(); column++) {
                    tiles[currentTile++] = new Tile(row,column,block[row][column].getBlock());
                }
            }
            board.setTiles(tiles);

            try {
                //Wzależności od rozmiaru planszy dopasowuje skalę zapisu
                switch (Settings.getBoardSize()) {
                    case 5:
                        Output.writeNonogram(board, 50,  "user_saved_nonograms/5x5/nonogram5x5.png");
                        break;
                    case 10:
                        Output.writeNonogram(board, 40,  "user_saved_nonograms/10x10/nonogram10x10.png");
                        break;
                    case 15:
                        Output.writeNonogram(board, 30,  "user_saved_nonograms/15x15/nonogram15x15.png");
                        break;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            MainFrame.cl.show(MainFrame.navigation, "HOMESCREEN");
        });

        /*
          Tworzenie siatki nonogramu
         */
        for (int row = 0; row < Settings.getBoardSize(); row++) {
            for(int column = 0; column < Settings.getBoardSize(); column++) {
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

        setOpaque(false);
        container.setOpaque(false);
        fieldContainer.setOpaque(false);


        setBackground(new Color(0,0,0,0));
        container.setBackground(new Color(0,0,0,0));
        fieldContainer.setBackground(new Color(0,0,0,0));


        c.gridx = 0;
        c.gridy = 0;
        container.add(homeButton, c);

        c.gridx = 1;
        c.gridy = 0;
        container.add(text, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.ipady = 20;
        container.add(fieldContainer, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.CENTER;
        container.add(randomButton, c);

        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.5;
        c.anchor=GridBagConstraints.CENTER;
        container.add(saveButton, c);

        add(container);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}

