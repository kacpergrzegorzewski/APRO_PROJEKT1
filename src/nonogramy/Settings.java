package nonogramy;
/*
Mam taki plan żeby wszystkie rzeczy które wybieramy w aplikacji były przechowywane w tej klasie (np wybrany rozmiar mapy)
 */

import nonogramy.entity.Block;
import nonogramy.entity.Board;
import nonogramy.entity.Tile;


public class Settings {
    private static int fieldSize=1;
    private static Tile[] tiles;

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
