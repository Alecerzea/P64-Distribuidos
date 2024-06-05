import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejadorCliente implements Runnable {
    private Socket clienteSocket;
    private PrintWriter salida;
    private BufferedReader entrada;

    public ManejadorCliente(Socket socket) throws IOException {
        clienteSocket = socket;
        salida = new PrintWriter(clienteSocket.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String mensajeCliente;
            while ((mensajeCliente = entrada.readLine()) != null) {
                // Aquí puedes procesar el mensaje recibido del cliente
                System.out.println("Mensaje recibido de " + clienteSocket.getInetAddress().getHostAddress() + ": " + mensajeCliente);

                // Ejemplo de enviar una respuesta al cliente
                salida.println("Mensaje recibido: " + mensajeCliente);
            }
        } catch (IOException e) {
            System.out.println("Cliente desconectado: " + clienteSocket.getInetAddress().getHostAddress());
        } finally {
            try {
                salida.close();
                entrada.close();
                clienteSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
