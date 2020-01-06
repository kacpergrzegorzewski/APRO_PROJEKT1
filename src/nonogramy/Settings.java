package nonogramy;
/*
Mam taki plan żeby wszystkie rzeczy które wybieramy w aplikacji były przechowywane w tej klasie (np wybrany rozmiar mapy)
 */

import nonogramy.components.Block;
import nonogramy.entity.Board;
import nonogramy.entity.Tile;


public class Settings {
    private static int fieldSize=1;
    private static Tile[] tiles;

    public static void setFieldSize(int fieldSize) {
        Settings.fieldSize = fieldSize;
    }

    public static int getFieldSize() { return fieldSize; }

    public static void setTiles(Tile[] board) { Settings.tiles = board; }

    public static Tile[] getTiles() { return tiles; }
}
