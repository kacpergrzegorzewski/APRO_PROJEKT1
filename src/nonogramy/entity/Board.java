package nonogramy.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    public int size; //Rozmiar planszy
    public Tile[] tiles; //Tablica wszystkich pól na planszy

    private ArrayList<ArrayList<Integer>> rowNumbers = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> columnNumbers = new ArrayList<>();

    //Konstruktor
    public Board(int size) {
        this.size = size;
        tiles = new Tile[size * size];
        //generateBoard();
    }

    public Board() {
    }

    public void generateRandomBoard() {
        RandomGenerator.generateRandomTiles(tiles);
        generateNumbers();
    }

    public void initBoard() {

    }

    public void printBoard() { //Wyświetla planszę w konsoli
        for (int i = 0; i < tiles.length; i++) {
            if (i != 0 && i % size == 0)
                System.out.println();

            if (tiles[i].isChecked()) //Jeżeli pole jest oznaczone jako zaznaczone to wyświetla X
                System.out.print(" X ");
            else
                System.out.print(" . ");
        }
    }

    //Zwraca tablicę wartości zaznaczeń kolejnych tiles'ów
    public boolean[] getBoard() {
        boolean[] value = new boolean[tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            value[i] = tiles[i].isChecked();
        }
        return value;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }

    public Tile[] getTiles() { return tiles; }

    private void generateNumbers() {
        //Zlicza zamalowane kratki w kolumnach i wierszach i dodaje je do tablic

        int inrow = 0; //ilosc kratek z rzedu w wierszu
        for (int j = 0; j < size; j++) {
            ArrayList<Integer> array = new ArrayList<>(); //lista pomocnicza
            for (int i = 0; i < size; i++) {


                if (tiles[j * size + i].isChecked()) //zlicza zamalowane kafelki z rzędu
                {
                    inrow++;
                } else if (i != 0 && tiles[j * size + i - 1].isChecked()) {  //warunek do dodawania kolejnych dlugości kafelków z rzędu, jezeli nie jest pierwsza i kafelek przed nia jest checked (jest co zapisac)
                    array.add(inrow);
                    inrow = 0;
                }
                if (i == size - 1 && tiles[j * size + i].isChecked()) {
                    array.add(inrow); //warunek na koniec wiersza
                    inrow = 0;
                }

            }
            //System.out.println(array);
            rowNumbers.add(array); // dodaje roboczą liste do naszej listy list
            //array.clear();
        }
        //System.out.println();

        int incol=0;

        for (int j = 0; j < size; j++) {
            ArrayList<Integer> array1 = new ArrayList<>();
            for (int i = 0; i < size; i++) {

                if (tiles[j + i * size].isChecked()) //zlicza zamalowane kafelki z rzędu w kolumnie
                {
                    incol++;
                } else if (i != 0 && tiles[j + (size) * (i-1)].isChecked()) {  //warunek do dodawania kolejnych dlugości kafelków z kolumny, jezeli nie jest pierwsza i kafelek nad nim jest checked (jest co zapisac)
                    array1.add(incol);
                    incol = 0;
                }
                if (i == size-1 && tiles[j + size * i].isChecked()) {
                    array1.add(incol); //warunek na koniec kolumny
                    incol = 0;
                }
            }
            //System.out.println(array1);
            columnNumbers.add(array1); // dodaje roboczą liste do naszej listy list
            //array1.clear();
        }
        System.out.println(rowNumbers);
        System.out.println(columnNumbers);
    }
}
