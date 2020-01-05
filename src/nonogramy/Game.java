package nonogramy;

import nonogramy.entity.Board;
import nonogramy.io.*;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {
        Board board = new Board(10);
        board.printBoard();
        Input nonogram = new Input("C:\\Users\\kac12\\OneDrive\\Pulpit\\studia\\APRO\\Projekt1\\test.png");
        nonogram.printNonogram();

        Output.writeNonogram(board, 10, "output_test.png");
    }
}
