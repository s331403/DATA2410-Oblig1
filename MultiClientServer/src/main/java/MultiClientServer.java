import org.springframework.beans.factory.annotation.Autowired;

import java.net.*;
import java.io.*;

public class MultiClientServer {

    @Autowired
    private ClientHandler clientHandler;

    private ServerSocket serverSocket;

    public void startServer(int port) throws IOException {
            serverSocket = new ServerSocket(port);
            while(true) {
                clientHandler = new ClientHandler(serverSocket.accept());
                clientHandler.start();
            }
    }

    public void stopServer() throws IOException {
        serverSocket.close();
    }

    public static void main(String[] args) {
        try {
            MultiClientServer server = new MultiClientServer();
            server.startServer(5555);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
