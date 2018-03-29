import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * A request handler for a server that performs simple arithmetic operations.
 *
 * <p>CS18000 -- Spring 2018 -- External Communication -- Homework</p>
 */
public final class ArithmeticRequestHandler implements Runnable {
    /**
     * The client socket of this request handler.
     */
    private Socket clientSocket;

    /*
     * Error message constants.
     */

    private static final String ILLEGAL_NUM_ARGUMENTS_ERROR_MESSAGE;

    private static final String ILLEGAL_OPERATION_NAME_ERROR_MESSAGE;

    private static final String ILLEGAL_FIRST_OPERAND_ERROR_MESSAGE;

    private static final String ILLEGAL_SECOND_OPERAND_ERROR_MESSAGE;

    static {
        ILLEGAL_NUM_ARGUMENTS_ERROR_MESSAGE = String.format("%s: requests must include an operation name, and two operands all separated by spaces\n", ArithmeticProtocol.ILLEGAL_REQUEST);

        ILLEGAL_OPERATION_NAME_ERROR_MESSAGE = String.format("%s: the operation name must be part of the protocol\n", ArithmeticProtocol.ILLEGAL_REQUEST);

        ILLEGAL_FIRST_OPERAND_ERROR_MESSAGE = String.format("%s: the first operand must be a valid integer\n", ArithmeticProtocol.ILLEGAL_REQUEST);

        ILLEGAL_SECOND_OPERAND_ERROR_MESSAGE = String.format("%s: the second operand must be a valid integer\n", ArithmeticProtocol.ILLEGAL_REQUEST);
    } //static

    /**
     * Constructs a newly allocated {@code ArithmeticRequestHandler} object with the specified client socket.
     *
     * @param clientSocket the client socket of this request handler
     * @throws IllegalArgumentException if the {@code clientSocket} argument is {@code null}
     */
    public ArithmeticRequestHandler(Socket clientSocket) throws IllegalArgumentException {
        if (clientSocket == null) {
            throw new IllegalArgumentException("clientSocket argument is null");
        } else {
            this.clientSocket = clientSocket;
        } //end if
    } //ArithmeticRequestHandler

    private String proc_cmd(String[] cmd) {
        if(cmd == null) return "something went really wrong here.";
        
        if(cmd.length != 3)
            return "ILLEGAL_REQUEST: requests must include an operation name, and two operands all separated by spaces";
    
    
        ArithmeticProtocol op;
        try {
            op=ArithmeticProtocol.valueOf(cmd[0]);
            if(op == ArithmeticProtocol.ILLEGAL_REQUEST) throw new IllegalArgumentException("really dude");
        } catch (IllegalArgumentException e) {
            return "ILLEGAL_REQUEST: the operation name must be part of the protocol";
        }
        
        int arg1, arg2;
        try {
            arg1 = Integer.valueOf(cmd[1]);
        } catch (IllegalArgumentException e) {
            return "ILLEGAL_REQUEST: the first operand must be a valid integer";
        }
    
        try {
            arg2 = Integer.valueOf(cmd[2]);
        } catch (IllegalArgumentException e) {
            return "ILLEGAL_REQUEST: the second operand must be a valid integer";
        }
        
        switch(op) {
            case ADD:
                return String.valueOf(arg1+arg2);
            case SUBTRACT:
                return String.valueOf(arg1-arg2);
            case MULTIPLY:
                return String.valueOf(arg1*arg2);
            case DIVIDE:
                return String.valueOf(arg1/arg2);
            default:
                return "something went reaaaaalllllyyyy wrong here.";
        }
    }
    
    /**
     * Communicates with the client, and processes their requests until they disconnect.
     */
    @Override
    public void run() {
        try {
            InputStream in = clientSocket.getInputStream();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            Scanner sc = new Scanner(in);
            
            while(true) {
                if(sc.hasNextLine()) {
                    String[] cmd = sc.nextLine().split(" ");
                    String ret = proc_cmd(cmd);
                    
                    out.println(ret);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    
    } //run
}