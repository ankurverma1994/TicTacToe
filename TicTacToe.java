/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class TicTacToe {
    int alpha = Integer.MIN_VALUE; // negative infinity
    int beta = Integer.MAX_VALUE; // positive infinity
    BufferedReader in;
    PrintWriter out;

    public static void main(String[] args) throws IOException {
        new TicTacToe().run();
    }

    public void run() throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        String player;
        String board[] = new String[3];

        //If player is X, I'm the first player.
        //If player is O, I'm the second player.
        player = in.readLine();

        //Read the board now. The board is a 3x3 array filled with X, O or _.
        for (int i = 0; i < 3; i++) {
            board[i] = in.readLine();
        }

        nextMove(player, board);
    }

    public void nextMove(String player, String board[]) {
        char game[][] = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game[i][j] = board[i].charAt(j);
            }
        }
    }

    public void minimax(char game[][], char player) {
        ArrayList<Point> available = getAvailableMoves(game);
        for (int i = 0; i < available.size(); i++) {
            Point p = available.get(i);
            int x = p.x, y = p.y;
            game[x][y] = player;
        }
    }

    public char winner(char game[][]) {
        /*left diagonal and right diagonal */
        if ((game[0][0] == game[1][1] && game[1][1] == game[2][2]) ||
                (game[0][2] == game[1][1] && game[1][1] == game[2][0]))
            return game[1][1];
        /*row winning */
        for (int i = 0; i < 3; i++) {
            if (game[i][0] == game[i][1] && game[i][1] == game[i][2])
                return game[i][0];
        }
        /*column winning*/
        for (int j = 0; j < 3; j++) {
            if (game[0][j] == game[1][j] && game[1][j] == game[2][j])
                return game[0][j];
        }
        // no winner
        return '#';
    }

    public ArrayList<Point> getAvailableMoves(char game[][]) {
        ArrayList<Point> available = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game[i][j] == '_') {
                    available.add(new Point(i, j));
                }
            }
        }
        return available;
    }
}

class Point {
    int x, y;

    Point(int a, int b) {
        x = a;
        y = b;
    }
}
