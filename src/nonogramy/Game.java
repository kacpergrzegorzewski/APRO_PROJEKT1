package nonogramy;

import nonogramy.entity.Board;
import nonogramy.entity.RandomGenerator;
import nonogramy.entity.Solver;
import nonogramy.io.*;

import java.io.IOException;

/**
 * Klasa do testowania w okienku konsoli
 */
public class Game {
    // metoda main
    public static void main(String[] args) throws IOException {
        Board board;
        board = Input.readNonogram(RandomGenerator.randomNonogramPath(5));
        board.solve();
        board.printBoard();
        System.out.println();
        System.out.println(board.getColsNumbers());
        System.out.println(board.getRowNumbers());
    }
}
