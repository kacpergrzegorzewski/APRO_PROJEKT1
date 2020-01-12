package nonogramy;
/*
Mam taki plan żeby wszystkie rzeczy które wybieramy w aplikacji były przechowywane w tej klasie (np wybrany rozmiar mapy)
 */


public class Settings {
    private static int fieldSize=1;

    public static void setFieldSize(int fieldSize) {
        Settings.fieldSize = fieldSize;
    }

    public static int getFieldSize() { return fieldSize; }
}
