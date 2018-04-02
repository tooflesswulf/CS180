/**
 * ChatUser
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 4/1/18
 *
 */

public interface ChatUser {
    public int getId();
    public String getName();
    public void sendRequest(ChatMessage msg);
}

//public class ChatUser {
//    int id;
//    String username;
//
//    public ChatUser(int id, String name) {
//        this.id = id;
//        this.username = name;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getName() {
//        return username;
//    }
//}