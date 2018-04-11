import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MessageHandler
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 4/1/18
 *
 */

class MessageHandler {
    private static final int MAX_NAME_SIZE = 60;
    private ChatServer server;
    
    private List<TicTacToeGame> games = new ArrayList<>();
    
    public MessageHandler(ChatServer server) {
        this.server = server;
    }
    
    void parseInput(String in, ChatUser from) {
        if(in.charAt(0) == '\\' || in.charAt(0) == '/') {
            String[] args = in.split(" ");
            String cmd = args[0].substring(1).toLowerCase();
            
            switch (cmd) {
                case "logout": case "exit":
                    logoutRequest(from);
                    return;
                case "list": case "ls":
                    listRequest(args, from);
                    return;
                case "dm": case "w": case "msg": case "whisper":
                    dmRequest(args, from);
                    return;
                case "ttt": case "tictactoe":
                    tttRequest(args, from);
                    return;
            }
        }
        from.sendRequest(new ChatMessage(in));
    }
    
    private void logoutRequest(ChatUser user) {
        user.sendRequest(new ChatMessage(ChatMessage.SERVER, user.getName() + " has logged out.", -1));
        user.sendRequest(new ChatMessage(ChatMessage.LOGOUT, "", user));
    }
    
    private void listRequest(String[] args, ChatUser user) {
        if(args.length == 1) {
            String content = clientListString(user.getId());
            user.sendSystemMessage(content);
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        for(String s: Arrays.copyOfRange(args, 1, args.length)) {
            String retval = "Could not user with this id.";
            
            try {
                ChatUser targ=findUser(Integer.valueOf(s));
                if (targ != null) retval = targ.getName();
            } catch(NumberFormatException e) {
                retval = "id must be an integer.";
            }
    
    
            sb.append(String.format("%s: %s\n", s, retval));
        }
        
        user.sendSystemMessage(sb.toString());
    }
    
    private void dmRequest(String[] args, ChatUser user) {
        if(args.length < 2) {
            String content = "Bad call. usage: " + args[0] + " [other name] [message]";
            user.sendSystemMessage(content);
            return;
        }
    
        String name = args[1];
        ChatUser targ = findUser(name);
        if(targ == null) {
            String content = name + " was not found in the user list.";
            user.sendSystemMessage(content);
            return;
        }
        if(user.equals(targ)) {
            user.sendSystemMessage("lol u cant dm yourself");
            return;
        }
        
        String msg = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
        user.sendRequest(new ChatMessage(msg, targ.getId()));
    }
    
    private void tttRequest(String[] args, ChatUser user) {
        String errorContent = "Bad call. Usage:\n  " +
                args[0] + " start [other name]\tStart a game.\n  " +
                args[0] + " show\t\t\tView all of your games & ids.\n  " +
                args[0] + " show [other name]\tDisplay that game's board.\n  " +
                args[0] + " place [other name] [n]\tPlace your marker at spot (n)\n  " +
                args[0] + " ff [game id]\t\tForfeit a game.\n" +
                "If you have only a single game, the [game id] can be omitted.";
        
        if(args.length < 2) {
            user.sendSystemMessage(errorContent);
            return;
        }
        
        String cmd = args[1];
        switch (cmd) {
            case "start":
                if(args.length != 3) {
                    errorContent = "Bad call. Usage:\n  " +
                            args[0]+" "+args[1]+" [other name]\tStart a game.";
                    break;
                }
                tttStart(args[2], user);
                return;
            case "show": case "ls":
                tttShow(args, user);
                return;
            case "place":
                tttPlace(args, user);
                return;
            case "ff": case "forfeit":
                tttForfeit(args, user);
                return;
        }
        switch (cmd) {
        
        }
        user.sendSystemMessage(errorContent);
    }
    
    private synchronized void tttStart(String otherName, ChatUser user) {
        ChatUser other = findUser(otherName);
        if(other == null) {
            user.sendSystemMessage("User " + otherName + "could not be found.");
            return;
        }
        if(user.equals(other)) {
            user.sendSystemMessage("You can't start a game with yourself!");
            return;
        }
        for(TicTacToeGame g : getUserGames(user)) {
            if(g.getOpponent(user).equals(other)) {
                user.sendSystemMessage("You can't start another game with the same person.");
                return;
            }
        }
        
        TicTacToeGame game = new TicTacToeGame(other, user);
        games.add(game);
        
        user.sendSystemMessage("You started a new Tic Tac Toe game with "+otherName);
        other.sendSystemMessage(user.getName()+" has started a new Tic Tac Toe game with you");
        
        game.getCurrentPlayer().sendSystemMessage("Your turn first!");
    }
    
    private void tttShow(String[] args, ChatUser user) {
        List<TicTacToeGame> yourGames = getUserGames(user);
        if(yourGames.size() == 0) {
            user.sendSystemMessage("You don't have any games.");
            return;
        }
        
        if(args.length == 2) {
            if(yourGames.size() == 1) {
                TicTacToeGame g = yourGames.get(0);
                user.sendSystemMessage(g.toString(user));
                return;
            }
            
            user.sendSystemMessage(tttListString(user, yourGames));
            return;
        } else if(args.length == 3) {
            
            int targID = getGameIDByOpponent(args[2], user);
            for(TicTacToeGame g:yourGames) {
                if(g.getID() == targID) {
                    user.sendSystemMessage(g.toString(user));
                    return;
                }
            }
            user.sendSystemMessage("Could not find a game with opponent name: "+args[2]);
        }
    }
    
    private void tttPlace(String[] args, ChatUser user) {
        List<TicTacToeGame> yourGames = getUserGames(user);
        if(yourGames.size() == 0) {
            user.sendSystemMessage("You don't have any games.");
            return;
        }
        if(args.length == 3) {
            if(yourGames.size() == 1) {
                TicTacToeGame g = yourGames.get(0);
                int loc;
                try {
                    loc=Integer.valueOf(args[2]);
                } catch(NumberFormatException e) {
                    user.sendSystemMessage("You have to place a number");
                    return;
                }
                
                if(!g.getCurrentPlayer().equals(user) || !g.place(loc)) {
                    user.sendSystemMessage("Something went wrong idk what");
                    return;
                }
                
                if(!g.isplaying()) {
                    removeGame(g.getID());
                    return;
                }
                
                user.sendSystemMessage(g.toString(user));
                ChatUser other = g.getOpponent(user);
                other.sendSystemMessage(g.toString(other));
                return;
            }
        
            user.sendSystemMessage("Please specify your opponent");
            return;
        } else if(args.length == 4) {
            int targID = getGameIDByOpponent(args[2], user);
            for(TicTacToeGame g:yourGames) {
                if(g.getID() == targID) {
                    int loc;
                    try {
                        loc = Integer.valueOf(args[3]);
                    } catch(NumberFormatException e) {
                        user.sendSystemMessage("You have to place a number");
                        return;
                    }
                    
                    if(!g.getCurrentPlayer().equals(user) || !g.place(loc)) {
                        user.sendSystemMessage("Something went wrong idk what");
                        return;
                    }
    
                    if(!g.isplaying()) {
                        removeGame(g.getID());
                        return;
                    }
                    
                    user.sendSystemMessage(g.toString(user));
                    ChatUser other = g.getOpponent(user);
                    other.sendSystemMessage(g.toString(other));
                    return;
                }
            }
            user.sendSystemMessage("Could not find a game with opponent: "+args[2]);
            return;
        }
        
        user.sendSystemMessage("Invalid call.\n  " +
                args[0] + " place [other name] [n]\tPlace your marker at spot (n)"
        );
    }
    
    private void tttForfeit(String[] args, ChatUser user) {
        List<TicTacToeGame> yourGames = getUserGames(user);
        if(yourGames.size() == 0) {
            user.sendSystemMessage("You don't have any games.");
            return;
        }
        
        if(args.length == 2) {
            if(yourGames.size() == 1) {
                TicTacToeGame g = yourGames.get(0);
                removeGame(g.getID());
                
                user.sendSystemMessage("You forfeited the match.");
                g.getOpponent(user).sendSystemMessage(
                        user.getName() + " forfeited match " + g.getID() + ":\n" + g.toString());
                return;
            }
            
            user.sendSystemMessage("Please specify which game:\n  " +
                    args[0] + " " + args[1] + "[other name]\t\tForfeit the game.");
            return;
        }
        
        StringBuilder error = new StringBuilder("");
        for(int i=2; i<args.length; ++i) {
            int ffID = getGameIDByOpponent(args[i], user);
            TicTacToeGame g = removeGame(ffID);
            
            if(g==null || !g.playerInGame(user)) {
                error.append(args[i]);
                error.append(" ");
                continue;
            }
            
            g.getOpponent(user).sendSystemMessage(user.getName() + " forfeited match " + g.getID() + ":\n" +
                    g.toString());
        }
        
        if(error.length() > 0) {
            user.sendSystemMessage("Could not remove the following game ids:\n  " + error.toString());
            return;
        }
        user.sendSystemMessage("Successfully removed the game(s) specified.");
    }
    
    private synchronized TicTacToeGame removeGame(int gameID) {
        for(Iterator<TicTacToeGame> it = games.iterator(); it.hasNext();) {
            TicTacToeGame g = it.next();
            
            if(g.getID() == gameID) {
                if(!g.isplaying()) {
                    String msg = g.getWinnerUser().getName() + " won game " + g.getID() + ":\n" + g.toString();
                    
                    for(ChatUser u : g.getPlayers()) {
                        u.sendSystemMessage(msg);
                    }
                }
                it.remove();
                return g;
            }
        }
        
        return null;
    }
    
    private synchronized List<TicTacToeGame> getUserGames(ChatUser user) {
        return games.stream()
                .filter(g -> g.playerInGame(user))
                .collect(Collectors.toList());
    }
    
    private synchronized int getGameIDByOpponent(String name, ChatUser user) {
        for(TicTacToeGame g : getUserGames(user)) {
            if(g.getOpponent(user).getName().equalsIgnoreCase(name)) return g.getID();
        }
        return -1;
    }
    
    private ChatUser findUser(int id) {
        for(ChatUser u : server.getClients()) {
            if(u.getId() == id)
                return u;
        }
        return null;
    }
    
    private ChatUser findUser(String name) {
        for(ChatUser u : server.getClients()) {
            if(u.getName().equalsIgnoreCase(name))
                return u;
        }
        return null;
    }
    
    private String clientListString(int requestFrom) {
        int[] sizes = {-1, MAX_NAME_SIZE};
        String[] headers = {"id", "name"};
        TablePrinter printer = new TablePrinter(headers, sizes);
        
        for(ChatUser user : server.getClients()) {
            boolean identifyUser = requestFrom==user.getId();
            if(identifyUser) continue;

            String[] toAdd = {String.valueOf(user.getId()), user.getName()};
            if(identifyUser) {
                toAdd[1] = "(you) " + user.getName();
            }
            
            printer.add(toAdd);
        }
        
        return printer.toString();
    }
    
    private String tttListString(ChatUser user, List<TicTacToeGame> games1) {
        String[] headers = {"game id", "Opponent Name", "turn"};
        int[] lengthLimits = {-1, MAX_NAME_SIZE, -1};
        TablePrinter printer = new TablePrinter(headers, lengthLimits);
        for(TicTacToeGame g:games1) {
            String[] vals = {String.valueOf(g.getID()), g.getOpponent(user).getName(), "theirs"};
        
            if(g.getCurrentPlayer().equals(user)) vals[2] = "yours";
            
            printer.add(vals);
        }
        return printer.toString();
    }
}