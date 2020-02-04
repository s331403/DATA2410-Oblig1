import java.io.*;
import java.net.*;

public class Client {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public String test ="clientdsda";


    public void connect(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void disconnect() throws IOException {
        socket.close();
        out.close();
        in.close();
    }
    public void send() {
        out.println("ping");
        try {
            System.out.println(in.readLine());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void send(String message) {
        out.println(message);
        String response;
        try {
            while((response = in.readLine()) != null )
            System.out.println(response);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
