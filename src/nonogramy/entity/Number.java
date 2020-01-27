package nonogramy.entity;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel wyswietlajacy kratki z liczbami
 */

public class Number extends JPanel  {
    private String text="";

    public Number(String text) {
        setPreferredSize(new Dimension(34, 34));
        this.text = text;
    }

    @Override
    protected void paintComponent(Graphics g) {
        setOpaque(false);
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(new Color(0,0,0,0));
        int height = 26;
        int width = 26;
        g2.drawRect(5, 5, width, height);

        Font font = new Font("Calibri", Font.PLAIN, 22);
        g2.setFont(font);

        g2.drawString(text, 8, 24);
    }



}
