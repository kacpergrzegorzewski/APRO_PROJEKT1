package nonogramy.entity;

import java.util.Random;

/**
 * Klasa sluzaca do generowania losowej planszy i losowej sciezki do gotowego nonogramu. Posiada metody statyczne
 */

public class RandomGenerator {
    public static void generateRandomTiles(Tile[] tiles) {
        Random random = new Random();
        int size = (int) Math.sqrt(tiles.length);

        for (int i = 0; i < tiles.length; i++) { //Dla każdego pola na planszy
            tiles[i] = new Tile(i%size, i/size, random.nextBoolean()); //Ustawia pozycje pola (x,y) i to czy jest zaznaczony
        }
    }

    public static String randomNonogramPath(int size) {
        Random random = new Random();
        return "nonograms/" + size + "x" + size + "/nonogram_" + (random.nextInt(10) + 1) + ".png";
    }
}
