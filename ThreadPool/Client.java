

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public Runnable getRunnable() {  // Runnable function( interface ) which takes or returns nothing, it only starts the run() method
        return new Runnable() {  // annonymous class definition
            @Override
            public void run() {  // overriding the run() method inside Runnable and use it as our own method
                int port = 8010;
                try {
                    InetAddress address = InetAddress.getByName("localhost"); // as server will run on our local machine, so it will give the IP of our machine( localhost )
                    Socket socket = new Socket(address, port);  // new socket created with our IP and defined port
                    try (
                        PrintWriter toServer = new PrintWriter(socket.getOutputStream());  // 
                        BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));  // 
                    ) {
                        toServer.println("Hello from client!");  // from client to server it is sent
                        String line = fromServer.readLine();  // getting response from server
                        System.out.println("Response from server :: " + line);
                        toServer.close();
                        fromServer.close();
                        socket.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        };
    }

    public static void main(String[] args) {
        Client client = new Client();
        for (int i = 0; i < 100; i++) {  // we are setting 100 threads for each client who will reach out to the server
            try {
                Thread thread = new Thread(client.getRunnable());  // 100 threads will be assigned for each client
                thread.start();  // threads will start creating for each client

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
