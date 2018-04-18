import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

final class ChatServer {
    private static int uniqueId = 0;
    // Data structure to hold all of the connected clients
    private final List<ClientThread> clients = new ArrayList<>();
    private final int port;			// port the server is hosted on
    
    private MessageHandler handler;

    /**
     * ChatServer constructor
     * @param port - the port the server is being hosted on
     */
    private ChatServer(int port) {
        this.port = port;
        handler = new MessageHandler(this);
    }

    /*
     * This is what starts the ChatServer.
     * Right now it just creates the socketServer and adds a new ClientThread to a list to be handled
     */
    private void start() {
        try {
            ServerSocket serverSocket=new ServerSocket(port);
            
            while(true) {
                Socket socket=serverSocket.accept();
                addClient(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private synchronized void send(ChatMessage msg, int fromID) {
        int recv_id = msg.getRecipient();
        
        List<ClientThread> sendTo = clients.stream()
                .filter(ct -> ct.id == recv_id || (recv_id==-1 ))
                .collect(Collectors.toList());
        
        for(ClientThread ct : sendTo) {
            ct.sendMessage(msg);
        }
    }
    
    private synchronized void addClient(Socket skt) {
        Runnable r=new ClientThread(skt, uniqueId++);
        Thread t=new Thread(r);
        clients.add((ClientThread) r);
        t.start();
    }
    
    private synchronized void removeClient(int clientID) {
        clients.removeIf((ClientThread x) -> x.id == clientID);
    }
    
    public synchronized List<ClientThread> getClients() {
        return clients;
    }
    
    /*
     * This is a private class inside of the ChatServer
     * A new thread will be created to run this every time a new client connects.
     */
    private final class ClientThread implements Runnable, ChatUser {
        Socket socket;                  // The socket the client is connected to
        ObjectInputStream sInput;       // Input stream to the server from the client
        ObjectOutputStream sOutput;     // Output stream to the client from the server
        String username;                // Username of the connected client
        int id;
        
        int messageCount;
        boolean living;

        /*
         * socket - the socket the client is connected to
         * id - id of the connection
         */
        private ClientThread(Socket socket, int id) {
            this.id = id;
            this.socket = socket;
            try {
                sOutput = new ObjectOutputStream(socket.getOutputStream());
                sInput = new ObjectInputStream(socket.getInputStream());
                username = (String) sInput.readObject();
                
                living = false;
                for(ClientThread ct : clients) {
                    if(ct.username.equalsIgnoreCase(username)) {
                        sOutput.writeObject(-1);
                        return;
                    }
                }
                System.out.println(username + " has connected.");
                
                sOutput.writeObject(id);
    
                messageCount = 0;
                living = true;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        
        synchronized void sendMessage(ChatMessage msg) {
            if(!living) return;
            try {
                sOutput.writeObject(msg);
            } catch(IOException e) {
                removeClient(id);
                living=false;
                if (e instanceof EOFException) {
                    System.out.println(username + " has disconnected.");
                    return;
                }
                e.printStackTrace();
            }
        }
        
        private synchronized void logout(ChatMessage cmd) {
            System.out.println(username + " has logged out.");
            
            sendMessage(cmd);
            
            living = false;
            removeClient(id);
        }
        
        public synchronized void sendRequest(ChatMessage msg) {
            switch (msg.getCmd()) {
                case ChatMessage.MESSAGE:
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                    String curTime = dateFormat.format(new Date());
        
                    String ret = String.format("[%s] %s: %s", curTime, username, msg.getContent());
                    if(msg.getRecipient()!=-1) {
                        ret=String.format("[%s] (DM from %s): %s", curTime, username, msg.getContent());
                    }
                    
                    send(new ChatMessage(ret, msg.getRecipient()), id);
                    break;
                case ChatMessage.LOGOUT:
                    logout(msg);
                    break;
//                case ChatMessage.LIST:
//                    ret = handler.clientListString(id);
//                    send(new ChatMessage(ret, msg.getRecipient()), id);
//                    break;
                case ChatMessage.SERVER:
                    send(msg, id);
                    break;
                default:
                    System.out.printf("SERVER: code %d not found\n", msg.getCmd());
            }
        }
        
        private synchronized void recieveMessage(String in) {
            System.out.printf("user `%s`:\t%s\n", username, in);
            handler.parseInput(in, this);
        }

        /*
         * This is what the client thread actually runs.
         */
        @Override
        public void run() {
            String cm;
            while(living) {
                // Read the username sent to you by client
                try {
                    cm = (String) sInput.readObject();
                    messageCount++;
                } catch (IOException|ClassNotFoundException e) {
                    removeClient(id);
                    if (e instanceof EOFException) {
                        System.out.println(username + " has disconnected.");
                        break;
                    }
                    
                    e.printStackTrace();
                    break;
                }
                
                recieveMessage(cm);
            }
            
            removeClient(id);
        }
    
        public int getId() { return id; }
        public String getName() { return username; }
    }
    
    
    /*
     *  > java ChatServer
     *  > java ChatServer portNumber
     *  If the port number is not specified 1500 is used
     */
    public static void main(String[] args) {
        int port = 1500;
        if(args.length == 1) {
            port = Integer.valueOf(args[0]);
        }
        
        ChatServer server = new ChatServer(port);
        server.start();
    }
}