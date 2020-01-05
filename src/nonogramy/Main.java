package nonogramy;

import java.awt.EventQueue;
import nonogramy.frames.MainFrame;
/**
Ta klasa tylko uruchamia okienko MainFrame
 */
public class Main {
    
    public static void main(String[] args) {
        //magiczy kod. Nie zagłębiajcie się.
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
        System.out.println(Settings.getFieldSize());
    }
}