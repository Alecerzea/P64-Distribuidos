import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejadorCliente extends Thread {
    private Socket clienteSocket;

    public ManejadorCliente(Socket clienteSocket) {
        this.clienteSocket = clienteSocket;
    }

    public void run() {
        try (
            PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()))
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Mensaje recibido de " + clienteSocket.getInetAddress().getHostAddress() + ": " + inputLine);
                out.println("Mensaje recibido: " + inputLine);
            }
            clienteSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
