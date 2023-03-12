public class Game {
    char[] board = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9' }; 
    public void changeState(int pos, char player) {
        board[pos] = player;
    }
}
