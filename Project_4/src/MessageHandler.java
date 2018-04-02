import java.util.Arrays;
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
    private ChatServer server;
    private final int MAX_LINE_SIZE = 50;
    
    public MessageHandler(ChatServer server) {
        this.server = server;
    }
    
    public void parseInput(String in, ChatUser from) {
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
        user.sendRequest(new ChatMessage(ChatMessage.LOGOUT, "", user.getId()));
    }
    
    private void listRequest(String[] args, ChatUser user) {
        if(args.length == 1) {
            String content = clientListString(user.getId());
            user.sendRequest(ChatMessage.call(content, user.getId()));
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        for(String s: Arrays.copyOfRange(args, 1, args.length)) {
            String retval = "Could not user with this id.";
            try {
                String name = findUserName(Integer.valueOf(s));
                if(name!=null) retval = name;
            } catch(Exception e) {}
            
            sb.append(String.format("%s: %s\n", s, retval));
        }
        
        user.sendRequest(ChatMessage.call(sb.toString(), user.getId()));
    }
    
    private void dmRequest(String[] args, ChatUser user) {
        if(args.length < 2) {
            String content = "Bad call. usage: " + args[0] + " [other name] [message]";
            user.sendRequest(ChatMessage.call(content, user.getId()));
            return;
        }
    
        String name = args[1];
        int targ = findUserID(name);
        if(targ == -1) {
            String content = name + " was not found in the user list.";
            user.sendRequest(ChatMessage.call(content, user.getId()));
            return;
        }
        if(targ == user.getId()) {
            user.sendRequest(ChatMessage.call("lol u cant dm yourself", user.getId()));
            return;
        }
        
        String msg = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
        user.sendRequest(new ChatMessage(msg, targ));
    }
    
    private void tttRequest(String[] args, ChatUser user) {
        String errorContent = "Bad call. Usage:\n  " +
                args[0] + " start [other name]\tStart a game.\n  " +
                args[0] + " show\t\t\tView all of your games & ids.\n  " +
                args[0] + " show [game id]\t\tDisplay that game's board.\n  " +
                args[0] + " place [id] [r] [c]\tPlace your marker at (r, c)\n" +
                "If you have only a single game, the [game id] can be omitted.";
        
        if(args.length < 2) {
            user.sendRequest(ChatMessage.call(errorContent, user.getId()));
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
                return;
        }
        user.sendRequest(ChatMessage.call(errorContent, user.getId()));
    }
    
    private void tttStart(String otherName, ChatUser user) {
        ChatUser other = findUser(otherName);
        if(other == null) {
            user.sendRequest(ChatMessage.call("User " + otherName + "could not be found.", user.getId()));
            return;
        }
        TicTacToeGame game = new TicTacToeGame(other, user);
        server.addGame(game);
        
        user.sendRequest(ChatMessage.call("You started a new Tic Tac Toe game with "+otherName, user.getId()));
        other.sendRequest(ChatMessage.call(user.getName()+" has started a new Tic Tac Toe game with you", other.getId()));
    }
    
    private void tttShow(String[] args, ChatUser user) {
        List<TicTacToeGame> games = getUserGames(user);
        if(games.size() == 0) {
            user.sendRequest(ChatMessage.call("You don't have any games.", user.getId()));
            return;
        }
        
        if(args.length == 2) {
            if(games.size() == 1) {
                user.sendRequest(ChatMessage.call(games.get(0).toString(), user.getId()));
                return;
            }
            StringBuilder sb = new StringBuilder("Your games:\n");
            for(TicTacToeGame g:games) {
                sb.append(g.getID());
            }
            return;
        } else if(args.length == 3) {
        
        }
    }
    
    private List<TicTacToeGame> getUserGames(ChatUser user) {
        return server.getGames().stream()
                .filter(g -> g.playerInGame(user))
                .collect(Collectors.toList());
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
    
    private int findUserID(String name) {
        for(ChatUser u : server.getClients()) {
            if(u.getName().equalsIgnoreCase(name)) {
                return u.getId();
            }
        }
        return -1;
    }
    
    private String findUserName(int id) {
        for(ChatUser u : server.getClients()) {
            if(u.getId() == id) {
                return u.getName();
            }
        }
        return null;
    }
    
    private String clientListString(int requestFrom) {
        char[] spaces;
        
        StringBuilder sb = new StringBuilder();
        
        spaces = new char[MAX_LINE_SIZE+1];
        Arrays.fill(spaces, '_');
        spaces[MAX_LINE_SIZE] = '\n';
        sb.append(spaces);
        
        Arrays.fill(spaces, ' ');
        System.arraycopy("|   id   | name".toCharArray(), 0, spaces, 0, 15);
        spaces[MAX_LINE_SIZE-1] = '|';
        spaces[MAX_LINE_SIZE] = '\n';
        sb.append(spaces);
        
        for(ChatUser user : server.getClients()) {
            boolean identifyUser = requestFrom == user.getId();
//            if(identifyUser) continue;
    
            String idstr = String.format("%6d", user.getId());
            System.arraycopy(idstr.toCharArray(), 0, spaces, 2, idstr.length());
            
//            sb.append(String.format("| %6d | ", user.getId()));
            int lineLen = 2+6+3;
            int maxNameLen = MAX_LINE_SIZE-lineLen-2;
            
            if(identifyUser) maxNameLen -= 5;
    
            if(user.getName().length() > maxNameLen) {
                System.arraycopy(user.getName().toCharArray(), 0, spaces, lineLen, maxNameLen-3);
                System.arraycopy("...".toCharArray(), 0, spaces, lineLen+maxNameLen-3, 3);
                if(identifyUser) {
                    System.arraycopy("(you)".toCharArray(), 0, spaces, lineLen + maxNameLen, 5);
                }
            } else {
                String name = String.format("%-" + maxNameLen + "s", user.getName());
                System.arraycopy(name.toCharArray(), 0, spaces, lineLen, maxNameLen);
                if(identifyUser) {
                    System.arraycopy("(you)".toCharArray(), 0, spaces, lineLen + user.getName().length(), 5);
                }
            }
            sb.append(spaces);
        }
        
        spaces = new char[MAX_LINE_SIZE];
        Arrays.fill(spaces, '_');
        spaces[0] = '|';
        spaces[MAX_LINE_SIZE-1] = '|';
        spaces[9] = '|';
        sb.append(spaces);
        
        return sb.toString();
    }
}