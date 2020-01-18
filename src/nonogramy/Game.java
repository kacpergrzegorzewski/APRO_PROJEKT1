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
    public static void main(String[] args) throws IOException {
        Board board;
        board = Input.readNonogram(RandomGenerator.randomNonogramPath(15));
        //board.printBoard();
        Solver s = new Solver(board);
        board.getColsNumbers();
        System.out.println(board.getColsNumbers());
        System.out.println("CHORE NAPISY");


        //
        //board.generateRandomBoard();
        //board.printBoard();

        //Output.writeNonogram(board, 10, "output_test.png");
    }
}
