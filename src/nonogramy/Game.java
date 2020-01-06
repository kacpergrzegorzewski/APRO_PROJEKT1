package nonogramy;

import nonogramy.entity.Board;
import nonogramy.entity.RandomGenerator;
import nonogramy.io.*;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {
        Input input = new Input();
        Board board = new Board(5);
        //board = input.readNonogram(RandomGenerator.randomNonogramPath(15));
        //board.printBoard();
        //
        board.generateRandomBoard();
        board.printBoard();

        //Output.writeNonogram(board, 10, "output_test.png");
    }
}
