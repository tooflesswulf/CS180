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
    int getId();
    String getName();
    void sendRequest(ChatMessage msg);
    
    default void sendSystemMessage(String content) {
        sendRequest(new ChatMessage(ChatMessage.SERVER, content, this));
    }
    default boolean equals(ChatUser other) {
        return getId() == other.getId();
    }
}
