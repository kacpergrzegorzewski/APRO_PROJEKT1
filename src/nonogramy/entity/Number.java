package nonogramy.entity;

import javax.swing.*;
import java.awt.*;

public class Number extends JPanel  {
    private String text="";



    public Number(String text) {
        setPreferredSize(new Dimension(34, 34));
        this.text = text;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(Color.WHITE);
        int height = 20;
        int width = 20;
        g2.drawRect(5, 5, width, height);

        Font font = new Font("Arial", Font.PLAIN, 24);
        g2.setFont(font);

        g2.drawString(text, 8, 24);
    }



}
