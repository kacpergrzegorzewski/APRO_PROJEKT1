package nonogramy;

import nonogramy.entity.Tile;

import java.awt.*;

/**
 * Mam taki plan zeby wszystkie rzeczy ktore wybieramy w aplikacji były przechowywane w tej klasie (np. wybrany rozmiar mapy)
 */
public class Settings {
    private static int fieldSize=1;
    private static Tile[] tiles=new Tile[0];

    public static Font homeFont = new Font("Arial", Font.PLAIN, 40);

    private static int boardSize = 1;

    public static void setBoardSize(int boardSize) {
        Settings.boardSize = boardSize;
    }

    public static int getFieldSize() { return fieldSize; }

    public static void setFieldSize(int fieldSize) {
        Settings.fieldSize = fieldSize;
    }


    public static void setTiles(Tile[] board) { Settings.tiles = board; }

    public static Tile[] getTiles() { return tiles; }
    public static int getBoardSize() { return boardSize; }
}
