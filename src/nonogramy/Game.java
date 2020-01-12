package nonogramy;

import nonogramy.entity.Board;
import nonogramy.entity.RandomGenerator;
import nonogramy.entity.Solver;
import nonogramy.io.*;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {
        Board board;
        board = Input.readNonogram(RandomGenerator.randomNonogramPath(15));
        Solver s = new Solver(board);

        //board.printBoard();
        //
        //board.generateRandomBoard();
        //board.printBoard();

        //Output.writeNonogram(board, 10, "output_test.png");
    }
}
