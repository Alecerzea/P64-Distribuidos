package rpc;
import java.net.*;
import java.io.*;

public class Server {
  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(3000);
    System.out.println("Server listening on port 3000");

    Socket socket = serverSocket.accept();
    System.out.println("Client connected");

    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

    String message;
    while ((message = reader.readLine()) != null) {
      System.out.println("Received message from client: " + message);
      writer.println("Hello from server!");
    }

    socket.close();
    serverSocket.close();
  }
}
