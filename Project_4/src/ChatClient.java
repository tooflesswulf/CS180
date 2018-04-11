import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

final class ChatClient {
    private ObjectInputStream sInput;
    private ObjectOutputStream sOutput;
    private Socket socket;
    private Thread t;
    private int myID;

    private final String server;
    private final String username;
    private final int port;

    /* ChatClient constructor
     * @param server - the ip address of the server as a string
     * @param port - the port number the server is hosted on
     * @param username - the username of the user connecting
     */
    private ChatClient(String server, int port, String username) {
        this.server = server;
        this.port = port;
        this.username = username;
    }

    /**
     * Attempts to establish a connection with the server
     * @return boolean - false if any errors occur in startup, true if successful
     */
    private boolean start() {
        // Create a socket
        try {
            socket = new Socket(server, port);
        } catch (IOException e) {
            System.out.println("Server is not online.");
            e.printStackTrace();
            return false;
        }

        // Attempt to create output stream
        try {
            sOutput = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Attempt to create input stream
        try {
            sInput = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // After starting, send the clients username to the server.
        try {
            sOutput.writeObject(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        try {
            myID = (int) sInput.readObject();
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    
        if(myID == -1) {
            System.out.printf("Name %s is already taken.\n", username);
            return false;
        }
        System.out.println(String.format("Successfully logged in as %s with id %d", username, myID));
        
        // Create client thread to listen from the server for incoming messages
        Runnable r = new ListenFromServer();
        t = new Thread(r);
        t.start();

        return true;
    }
    
    /**
     * Returns whether this client is still connected to the server or not.
     */
    private boolean connected() {
        return t.isAlive();
    }
    
    /*
     * Sends a string to the server
     * @param msg - the message to be sent
     */
    private void sendMessage(String msg) {
        try {
            sOutput.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void executeMessage(ChatMessage msg) {
        switch (msg.getCmd()) {
            case ChatMessage.LOGOUT:
                System.out.println("Logged out.");
                System.exit(0);
            default:
                System.out.println(msg.getContent());
        }
    }
    
    /*
     * This is a private class inside of the ChatClient
     * It will be responsible for listening for messages from the ChatServer.
     * ie: When other clients send messages, the server will relay it to the client.
     */
    private final class ListenFromServer implements Runnable {
        public void run() {
            while(true) {
                try {
                    ChatMessage msg=(ChatMessage) sInput.readObject();
                    executeMessage(msg);
                } catch (IOException|ClassNotFoundException e) {
                    if(e instanceof EOFException) {
                        System.out.println("Server has stopped.");
                        System.exit(0);
                    }
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
    }
    
    
    /*
     * To start the Client use one of the following command
     * > java ChatClient
     * > java ChatClient username
     * > java ChatClient username portNumber
     * > java ChatClient username portNumber serverAddress
     *
     * If the portNumber is not specified 1500 should be used
     * If the serverAddress is not specified "localHost" should be used
     * If the username is not specified "Anonymous" should be used
     */
    public static void main(String[] args) {
        // Get proper arguments and override defaults
        String name;
        String server = "localhost";
        int port = 1500;
        
        if(args.length == 0) {
            System.out.println("Please specify a username.");
            return;
        }
        
        name=args[0];
        if(args.length > 1) {
            port = Integer.valueOf(args[1]);
        }
        if(args.length > 2) {
            server = args[2];
        }
        
        // Create your client and start it
        ChatClient client = new ChatClient(server, port, name);
        if(!client.start()) {
            System.exit(1);
        }
        
        Scanner sc = new Scanner(System.in);
        while(client.connected()) {
            String msg = sc.nextLine();
            if(msg.length() > 0) {
//                client.sendMessage(client.parseInput(msg));
                client.sendMessage(msg);
            }
        }
    }
}