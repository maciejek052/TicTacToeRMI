import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ServerImpl extends UnicastRemoteObject implements Interface {

    int gameID = 0;
    char turn = 'X';
    Boolean client1 = false, client2 = false;
    HashMap<Integer, Game> games = new HashMap<>();

    protected ServerImpl() throws RemoteException {
        super();
    }

    public void newGame() throws RemoteException {
        Game game = new Game();
        System.out.println("Starting game number " + gameID);
        gameID++;
        games.put(gameID, game);
    }

    public char registerClient() throws RemoteException {
        if (!client1) {
            System.out.println("Client 1 joined");
            client1 = true;
            return 'X';
        } else if (!client2) {
            newGame();
            System.out.println("Client 2 joined");
            client2 = true;
            return 'O';
        } else {
            System.out.println("Another client tried to join");
            return '0';
        }
    }

    public void makeMove(int position, char player) throws RemoteException {
        Game game = games.get(gameID);
        game.changeState(position, player);
        if (turn == 'X') {
            turn = 'O';
        } else {
            turn = 'X';
        }
    }

    public boolean gameReady() throws RemoteException {
        return client2;
    }

    public char playerTurn() throws RemoteException {
        return turn;
    }

    public char[] board() throws RemoteException {
        Game game = games.get(gameID);
        char[] board = game.board;
        return board;
    }

    public char checkGameState() throws RemoteException {
        Game game = games.get(gameID);
        char[] board = game.board;
        if (board[0] == board[1] && board[1] == board[2]) {
            return board[0];
        } else if (board[3] == board[4] && board[4] == board[5]) {
            return board[3];
        } else if (board[6] == board[7] && board[7] == board[8]) {
            return board[6];
        } else if (board[0] == board[3] && board[3] == board[6]) {
            return board[0];
        } else if (board[1] == board[4] && board[4] == board[7]) {
            return board[1];
        } else if (board[2] == board[5] && board[5] == board[8]) {
            return board[2];
        } else if (board[0] == board[4] && board[4] == board[8]) {
            return board[0];
        } else if (board[2] == board[4] && board[4] == board[6]) {
            return board[2];
        } else if (board[0] != '1' && board[1] != '2' && board[2] != '3'
                && board[3] != '4' && board[4] != '5' && board[5] != '6'
                && board[6] != '7' && board[7] != '8' && board[8] != '9') {
            return 'd';
        }
        return 'n';
    }

    public int getGameID() throws RemoteException {
        return gameID;
    }
}
