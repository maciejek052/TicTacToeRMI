import java.rmi.Naming;
import java.util.Scanner;

public class TicTacToeClient {

    private static void printBoard(char[] board) {
        System.out.println(board[0] + "|" + board[1] + "|" + board[2]);
        System.out.println("-----");
        System.out.println(board[3] + "|" + board[4] + "|" + board[5]);
        System.out.println("-----");
        System.out.println(board[6] + "|" + board[7] + "|" + board[8]);
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("java.security.policy", "security.policy");
        System.setSecurityManager(new SecurityManager());
        Interface obj = (Interface) Naming.lookup("//localhost/ABC");
        char player = obj.registerClient();
        if (player == '0') {
            System.out.println("You cannot join because there are already 2 clients connected");
            System.exit(1);
        }
        System.out.println("Welcome to Tic-Tac-Toe! You play as " + player);
        if (!obj.gameReady()) {
            System.out.println("Waiting for player 2...");
        }
        while (true) {
            if (obj.gameReady()) {
                System.out.println("Both players are connected, starting new game number " + obj.getGameID());
                while (true) {
                    char[] board = obj.board();
                    int gameNo = obj.getGameID();
                    switch (obj.checkGameState()) {
                        case 'O':
                            if ('O' == player) {
                                printBoard(board); 
                                System.out.println("Congratulations! You win. Starting next game number " + gameNo);
                                obj.newGame();
                            } else {
                                printBoard(board); 
                                System.out.println("Sorry! You lost. Starting next game number " + gameNo);
                                obj.newGame();
                            }
                            break;
                        case 'X':
                            if ('X' == player) {
                                printBoard(board); 
                                System.out.println("Congratulations! You win. Starting next game number " + gameNo);
                                obj.newGame();
                            } else {
                                printBoard(board); 
                                System.out.println("Sorry! You lost. Starting next game number " + gameNo);
                                obj.newGame();
                            }
                            break;
                        case 'd':
                            printBoard(board); 
                            System.out.println("It's a draw! Starting next game number " + gameNo);
                            obj.newGame();
                            break;
                        case 'n':
                            break;
                    }
                    board = obj.board();
                    boolean print = true;
                    while (obj.playerTurn() != player) {
                        if (print) {
                            printBoard(board);
                            System.out.println("Wait for opponent's move...");
                            print = false;
                        }
                    }
                    if (print) {
                        int t = 0;
                        printBoard(board);
                        System.out.println("Your turn, choose your field");
                        boolean ok = false;
                        while (ok == false) {
                            Scanner sc = new Scanner(System.in);
                            t = sc.nextInt() - 1;
                            if (board[t] == 'X' || board[t] == 'O' || t > 10) {
                                System.out.println("Wrong input, try again");
                            } else {
                                ok = true;
                            }
                        }
                        obj.makeMove(t, player);
                        print = false;
                    }

                }
            }
        }
    }
}
