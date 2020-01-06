package nonogramy.io;

import nonogramy.entity.Board;
import nonogramy.entity.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Klasa wczytująca nonogram
public class Input {
    private int width; //Szerokość
    private int height; //Wysokość
    public int[] pixels; //Liczba pixeli w pliku png

    //Konstruktor
    public Input() {
        //readNonogram(path);
    }

    public Board readNonogram(String path) throws IOException {
        int mask = 0x00ffffff; //Maska usuwająca kanał alpha
        BufferedImage image = ImageIO.read(new File(path)); //Tworzenia strumienia pobierania danych z pliku (obrazu)
        width = image.getWidth(); //Pobranie szerokości i zapisanie jej
        height = image.getHeight(); //Pobranie wysokości i zapisanie jej
        pixels = new int[width * height]; //Utworzenie tablicy pixeli

        //Dla każdego pixela ustawia jego wartość w zależnoci od koloru (z filtrowanie kanału alpha)
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[(y * width) + x] = image.getRGB(x, y) & mask;
            }
        }

        //Tworzę pomocnicze obiekty
        Tile[] tilesToFill = new Tile[pixels.length];
        Board board = new Board(width);

        //Zamieniam tablicę pikseli na tablicę pól
        for (int i = 0; i < pixels.length; i++) {
            tilesToFill[i] = new Tile(i%width, i/width, pixels[i] == 0x000000);
        }

        //Wstawiam pola do planszy
        board.setTiles(tilesToFill);

        return board;
    }

    //Wyświetla wczytany nonogram w konsoli
/*    public void printNonogram() {
        for (int i = 0; i < pixels.length; i++) {
            if (i != 0 && i % width == 0)
                System.out.println();

            if (pixels[i] == 0x000000)
                System.out.print(" X ");
            else
                System.out.print(" . ");
        }
    }*/
}
