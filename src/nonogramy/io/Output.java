package nonogramy.io;

import nonogramy.Settings;
import nonogramy.entity.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

/**
 * Klasa umożliwiająca zapis nonogramu do png
 */
//Klasa zapisująca nonogram
public class Output {
    public static void writeNonogram(Board board, int scale, String path) throws IOException {
        BufferedImage image = new BufferedImage(Settings.getBoardSize(), Settings.getBoardSize(), BufferedImage.TYPE_INT_RGB); //Tworzenie strumienia o wielkości podanej planszy i trybie zapisu RGB
        WritableRaster raster = image.getRaster();

        int[] pixels = new int[Settings.getBoardSize() * Settings.getBoardSize()]; //Tworzenie tablicy pixeli o wielkości pól na planszy

        convertTiles(board, pixels);

        raster.setDataElements(0, 0, Settings.getBoardSize(), Settings.getBoardSize(), pixels); //Zapisuje do obrazu rastrowego podaną tablice pixeli
        image.setData(raster); //Podmienia raster z danymi do strumienia

        image = resizeImage(image, scale); //Zmienia rozmiar obrazu zapisywanego, aby był czytelny

        ImageIO.write(image, "png", new File(path)); //Zapisuje obraz
    }

    //Zamienia pola na podanej planszy w zależności od ich zaznaczenia na odpowiednie wartości kolorów i zapisuje je w podanej tablicy pixeli
    private static void convertTiles(Board board, int[] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            if (board.tiles[i].isChecked())
                pixels[i] = 0x000000;
            else
                pixels[i] = 0xffffff;
        }
    }

    //Zmienia rozmiar obrazu zapisywanego, aby był czytelny
    private static BufferedImage resizeImage(BufferedImage image, int scale) {
        Image temp = image.getScaledInstance(image.getWidth(null) * scale, image.getHeight(null) * scale, Image.SCALE_DEFAULT);
        BufferedImage newImage = new BufferedImage(image.getWidth() * scale, image.getHeight() * scale, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = newImage.createGraphics();
        g.drawImage(temp, 0, 0, null);
        g.dispose();

        return newImage;
    }
}
