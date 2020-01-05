package nonogramy.components;

import nonogramy.frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;

public class Block extends JPanel  {
    private static final int PREF_W = 20;
    private static final int PREF_H = PREF_W;
    private boolean isEnabled;
    private boolean isCrossed;


    public Block() {
        setPreferredSize(new Dimension(34, 34));
        isEnabled = false;
        isCrossed = false;
    }

    public void setBlock(boolean isEnabled) { this.isEnabled=isEnabled; }

    public boolean getBlock() { return isEnabled; }

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
        int height = 30;
        int width = 30;
        g2.drawRect(0, 0, width, height);

        Font font = new Font("Arial", Font.PLAIN, 24);
        g2.setFont(font);

        if(isEnabled) g2.fillRect(3,3, width -5, height -5);
        if(isCrossed) g2.drawString("X", 8, 24);
    }



}
