package nonogramy;
/*
Mam taki plan żeby wszystkie rzeczy które wybieramy w aplikacji były przechowywane w tej klasie (np wybrany rozmiar mapy)
 */


public class Settings {
    private static int boardSize = 1;

    public static void setBoardSize(int boardSize) {
        Settings.boardSize = boardSize;
    }

    public static int getBoardSize() { return boardSize; }
}
