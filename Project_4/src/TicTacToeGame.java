import java.util.Arrays;
import java.util.Random;

public class TicTacToeGame {
    private static final int BOARD_SIZE = 3;
    
    public enum tile{X, O, TIE}
    private tile[][] board = new tile[BOARD_SIZE][BOARD_SIZE];;
    
    private int[] diags = {0, 0};
    private int[] rows = new int[BOARD_SIZE];
    private int[] cols = new int[BOARD_SIZE];
    
    private int turnNum = 0;
    private tile whoseTurn = tile.X;
    private tile winner = null;
    
    private ChatUser player1;
    private ChatUser player2;
    
    private static int idGenerator=0;
    private int gameID;
    
    public TicTacToeGame(ChatUser player1, ChatUser player2) {
//        if(Math.random() > .5) {
            this.player1=player2;
            this.player2=player1;
//        } else {
//            this.player1 = player2;
//            this.player2 = player1;
//        }
        
        gameID = idGenerator;
        idGenerator++;
    }
    
    /**
     * Returns the ID of the winner. A tie returns -1, and no winner yet return -2.
     */
    public ChatUser getWinnerUser() {
        if(winner == tile.X) return player1;
        if(winner == tile.O) return player2;
        return null;
    }
    
    public int getID() {
        return gameID;
    }
    
    public tile getWinner() {
        return winner;
    }
    
    public boolean isplaying() {
        return winner == null;
    }
    
    public boolean playerInGame(ChatUser playerID) {
        return playerID.getId()==player1.getId() || playerID.getId()==player2.getId();
    }
    
    public ChatUser getCurrentPlayer() {
        if(!isplaying()) return null;
        if(whoseTurn == tile.X) return player1;
        else return player2;
    }
    
    public ChatUser[] getPlayers() {
        ChatUser[] players = {player1, player2};
        return players;
    }
    
    public ChatUser getOpponent(ChatUser u) {
        if(!playerInGame(u)) return null;
        
        if(u.equals(player1)) return player2;
        return player1;
    }
    
    public boolean place(int spot) {
        return place(spot/3, spot%3);
    }
    
    public boolean place(int r, int c) {
        if(!isplaying()) {
            return false;
        }
        tile loc;
        try {
            loc = board[r][c];
        } catch(IndexOutOfBoundsException e) {
            return false;
        }
        if(loc == null) {
            board[r][c] = whoseTurn;
            
            rows[r] += tile2int(whoseTurn);
            cols[c] += tile2int(whoseTurn);
            if(r==c) diags[0] += tile2int(whoseTurn);
            if(r==BOARD_SIZE-1-c) diags[1] += tile2int(whoseTurn);
            
            if(Math.abs(rows[r]) == BOARD_SIZE || Math.abs(cols[c]) == BOARD_SIZE ||
                    Math.abs(diags[0]) == BOARD_SIZE || Math.abs(diags[1]) == BOARD_SIZE) {
                winner = whoseTurn;
                return true;
            }
            
            nextTurn();
            return true;
        }
        return false;
    }
    
    private void nextTurn() {
        if(++turnNum == 9) {
            winner = tile.TIE;
        }
        
        if(whoseTurn == tile.X) whoseTurn= tile.O;
        else if(whoseTurn == tile.O) whoseTurn= tile.X;
    }
    
    private int tile2int(tile t) {
        if(t == tile.X) return 1;
        if(t == tile.O) return -1;
        
        return 0;
    }
    
    private char tile2char(tile t) {
        if(t == tile.X) return 'X';
        if(t == tile.O) return 'O';
        return ' ';
    }
    
    public String toString() {
        char[] hline = new char[3*BOARD_SIZE + BOARD_SIZE-1 + 2];
        Arrays.fill(hline, '-');
        for(int i=4; i<hline.length; i+=4) hline[i] = '+';
        hline[0] = '\n';
        hline[hline.length-1] = '\n';
        
        String div = String.valueOf(hline);
        
        
        String[] cols = new String[BOARD_SIZE];
        for(int r=0; r<BOARD_SIZE; ++r) {
            String[] row = new String[BOARD_SIZE];
            for(int c=0; c<BOARD_SIZE; ++c) {
                char[] num = "   ".toCharArray();
                num[1] = tile2char(board[r][c]);
                row[c] = String.valueOf(num);
            }
            cols[r] = String.join("|", row);
        }
        
        return String.join(div, cols);
    }
    
    public String toString(ChatUser user) {
        if(!playerInGame(user)) return toString();
    
        String pre = String.format("Game %d vs %s:\n", getID(), getOpponent(user).getName());
        
        String end = "";
        if(user.equals(getCurrentPlayer())) {
            end = "\nYour turn!";
        }
        
        return pre + toString() + end;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TicTacToeGame) {
            TicTacToeGame otherGame = (TicTacToeGame) obj;
            return gameID == otherGame.gameID;
        }
        
        return super.equals(obj);
    }
}