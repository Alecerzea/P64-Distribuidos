import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteTCP {
    public static void main(String[] args) {
        final String SERVIDOR = "localhost";
        final int PUERTO = 22;

        try (
            Socket socket = new Socket(SERVIDOR, PUERTO);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Mensaje del servidor: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
