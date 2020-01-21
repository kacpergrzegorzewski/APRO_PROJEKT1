package nonogramy.entity;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel wyswietlajacy pojedynczy blok do gry
 */
public class Block extends JPanel  {
    private static final int PREF_W = 20;
    private static final int PREF_H = PREF_W;
    private boolean isEnabled;
    private boolean isCrossed;
    private int height = 30;  //wysokość obramowania
    private int width = 30;  //szerokość obramowania

    /**
     * Konstruktor
     */
    public Block() {
        setPreferredSize(new Dimension(34, 34));  //szerokość i wysokość bloku razem z marginesami
        isEnabled = false;
        isCrossed = false;
    }

    /**
     * Setter
     * @param isEnabled wartosc zaznaczenia
     */
    public void setBlock(boolean isEnabled) { this.isEnabled=isEnabled; }

    /**
     * Getter
     * @return Wartosc zanaczenia
     */
    public boolean getBlock() { return isEnabled; }

    /**
     * Zmienia wartosc zaznaczenia bloku przy kliknieciu
     */
    public void click() {
        if(isEnabled) {
            isEnabled = false;
            isCrossed = true;
        }
        else if(isCrossed) {
            isCrossed = false;
        }
        else{
            isEnabled = true;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(Color.WHITE);
        g2.drawRect(0, 0, width, height);

        Font font = new Font("Arial", Font.PLAIN, 24);
        g2.setFont(font);

        if(isEnabled) g2.fillRect(3,3, width -5, height -5); //-5 aby wypełnić tylko część wnętrza, a współrzędne ustawiają po środku
        if(isCrossed) g2.drawString("X", 8, 24);  //Rysowanie znaku "X" we wnętrzu
    }
}
