import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote {
    public void newGame() throws RemoteException; 
    public char registerClient() throws RemoteException;
    public void makeMove(int position, char player) throws RemoteException;
    public boolean gameReady() throws RemoteException; 
    public char playerTurn() throws RemoteException; 
    public char[] board() throws RemoteException; 
    public char checkGameState() throws RemoteException; 
    public int getGameID() throws RemoteException; 
}
