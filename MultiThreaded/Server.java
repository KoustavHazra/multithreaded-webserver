import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> getConsumer() {
        return (clientSocket) -> {
            try {
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
                toClient.println("Hello from server");
                toClient.close();
                clientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
    
    public static void main(String[] args) {
        int port = 8010;
        Server server = new Server();
        try {
            ServerSocket socket = new ServerSocket(port);
            socket.setSoTimeout(10000);
            System.out.println("Server is listening on port " + port);
            while (true) {
                try {
                    Socket acceptedConnection = socket.accept();  // any connection comes within 10s, this will accept that; after 10s it will be closed
                    Thread thread = new Thread(() -> server.getConsumer().accept(acceptedConnection));  // after a connection is set, at first we will create multiple threads instead of sending any data to client
                    // this new thread will run a function and through that function we will communicate with the client
                    
                    thread.start();  // start the thread

                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
