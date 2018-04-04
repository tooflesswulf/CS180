import java.io.Serializable;

final class ChatMessage implements Serializable {
    private static final long serialVersionUID=6898543889087L;
    
    // Types of messages
    static final int MESSAGE=0, LOGOUT=1, DM=2, LIST=3, TICTACTOE=4, SERVER=5;
    
    // Here is where you should implement the chat message object.
    // Variables, Constructors, Methods, etc.
    private int cmd;
    private String content;
    private int recipient;
    
    public ChatMessage(String content) {
        this(content, -1);
    }
    
    public ChatMessage(String content, int recipient) {
        this(0, content, recipient);
    }
    
    public ChatMessage(int cmd, String content, int recipient) {
        this.cmd=cmd;
        this.content=content;
        this.recipient=recipient;
    }
    
    public ChatMessage(int cmd, String content, ChatUser recipient) {
        this(cmd, content, recipient.getId());
    }
    
    
    public int getCmd() { return cmd; }
    public String getContent() { return content; }
    public int getRecipient() { return recipient; }
}

