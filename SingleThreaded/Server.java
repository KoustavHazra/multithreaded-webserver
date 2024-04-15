// package Webserver.SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    public void run() throws IOException {
        int port = 8010;  // port define
        ServerSocket socket = new ServerSocket(port);  // creating a socket in the defined port
        socket.setSoTimeout(10000);  // socket will be open till 10s and then it will close automatically
        while (true) {
            try {
                System.out.println("Server is listening on port " + port);
                Socket acceptedConnection = socket.accept();  // any connection comes within 10s, this will accept that; after 10s it will be closed
                System.out.println("Connection accepted from client " + acceptedConnection.getRemoteSocketAddress()); // if it gets any acceptedConntection, it will be printed
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream()); // 
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream())); // 
                toClient.println("Hello from server !!");  // server sending this message to client
                toClient.close();
                fromClient.close();
                acceptedConnection.close();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}