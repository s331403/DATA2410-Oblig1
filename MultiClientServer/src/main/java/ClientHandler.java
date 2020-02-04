import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

@Component
public class ClientHandler extends Thread {
    private Socket socket;
    private InetAddress ipAddress;
    private int serverPort;
    private int clientPort;


    public ClientHandler(Socket socket) {
        this.socket = socket;
        ipAddress = socket.getInetAddress();
        clientPort = socket.getPort();
        serverPort = socket.getLocalPort();
    }

    @Override
    public void run() {
        try {
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientInput;
            EmailExtractor extractor = new EmailExtractor();
            while((clientInput = input.readLine()) != null) {
                String emails = extractor.urlSearch(clientInput);
                System.out.println(emails);
            }
            socket.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
