import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MultiClientServerTest {

    private static MultiClientServer server;
    @BeforeAll
    static void startServer() {
        new Thread() {
            @Override
            public void run() {
                server = new MultiClientServer();
                try {
                    server.startServer(5555);
                } catch(IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }.start();
    }
    @org.junit.jupiter.api.Test
    void multiClientTest() {
        try {
            Client client1 = new Client();
            client1.connect("127.0.0.1", 5555);
            client1.send("https://stackoverflow.com/questions/8204680/java-regex-email/");
            client1.disconnect();

            //server.stopServer();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void stopServer() {
    }

    @AfterAll
    static void shutdown() {
        try {
            server.stopServer();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}