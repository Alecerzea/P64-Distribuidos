package rpc;
import java.net.*;
import java.io.*;

public class Client {
  public static void main(String[] args) throws UnknownHostException, IOException {
    Socket socket = new Socket("localhost", 3000);
    System.out.println("Connected to server");

    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    writer.println("Hello from client!");
    String message = reader.readLine();
    System.out.println("Received message from server: " + message);

    socket.close();
  }
}
