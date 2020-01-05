package nonogramy.entity;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    public int size; //Rozmiar planszy
    public Tile[] tiles; //Tablica wszystkich pól na planszy

    private ArrayList<Integer[]> rowNumbers = new ArrayList<>();
    private ArrayList<Integer[]> columnNumbers = new ArrayList<>();

    //Konstruktor
    public Board(int size) {
        this.size = size;
        tiles = new Tile[size * size];
        generateBoard();
    }

    public void generateBoard() {
        Random random = new Random(); //Inicjalizuje generator pseudolosowy
        for (int i = 0; i < tiles.length; i++) { //Dla każdego pola na planszy
            tiles[i] = new Tile(i%size, i/size, random.nextBoolean()); //Ustawia pozycje pola (x,y) i to czy jest zaznaczony
        }
        generateNumbers();
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
                value[i]=tiles[i].isChecked();
        }
        return value;
    }

    public void setTiles(Tile[] tiles) { this.tiles = tiles; }
    private void generateNumbers() {
        //Zlicza zamalowane kratki w kolumnach i wierszach i dodaje je do tablic
    }
}
