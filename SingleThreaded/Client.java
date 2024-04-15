// package Webserver.SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public void run() throws UnknownHostException, IOException {
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost"); // as server will run on our local machine, so it will give the IP of our machine( localhost )
        Socket socket = new Socket(address, port);  // new socket created with our IP and defined port
        PrintWriter toServer = new PrintWriter(socket.getOutputStream());  // 
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));  // 
        toServer.println("Hello from client!");  // from client to server it is sent
        String line = fromServer.readLine();  // getting response from server
        System.out.println("Response from server :: " + line);
        toServer.close();
        fromServer.close();
        socket.close();
    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.run();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
