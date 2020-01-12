package nonogramy.io;

import nonogramy.Settings;
import nonogramy.entity.Board;
import nonogramy.entity.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Klasa wczytująca nonogram
public class Input {
    public static int[] pixels; //Liczba pixeli w pliku png

    //Konstruktor
    public Input() {
        //readNonogram(path);
    }

    public static Board readNonogram(String path) throws IOException {
        int mask = 0x00ffffff; //Maska usuwająca kanał alpha
        BufferedImage image = ImageIO.read(new File(path)); //Tworzenia strumienia pobierania danych z pliku (obrazu)
        Settings.setBoardSize(image.getWidth());  //Pobranie szerokości i zapisanie jej
        pixels = new int[Settings.getBoardSize() * Settings.getBoardSize()]; //Utworzenie tablicy pixeli

        //Dla każdego pixela ustawia jego wartość w zależnoci od koloru (z filtrowanie kanału alpha)
        for (int y = 0; y < Settings.getBoardSize(); y++) {
            for (int x = 0; x < Settings.getBoardSize(); x++) {
                pixels[(y * Settings.getBoardSize()) + x] = image.getRGB(x, y) & mask;
            }
        }

        //Tworzę pomocnicze obiekty
        Tile[] tilesToFill = new Tile[pixels.length];
        Board board = new Board(Settings.getBoardSize());

        //Zamieniam tablicę pikseli na tablicę pól
        for (int i = 0; i < pixels.length; i++) {
            tilesToFill[i] = new Tile(i%Settings.getBoardSize(), i/Settings.getBoardSize(), pixels[i] == 0x000000);
        }

        //Wstawiam pola do planszy
        board.setTiles(tilesToFill);

        board.generateNumbers();

        return board;
    }
}
