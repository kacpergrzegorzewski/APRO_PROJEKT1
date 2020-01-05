package nonogramy;

import nonogramy.entity.Board;
import nonogramy.io.*;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {
        Board board = new Board(10);
        board.printBoard();

        Output.writeNonogram(board, 10, "output_test.png");
    }
}
