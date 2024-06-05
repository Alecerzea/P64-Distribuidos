import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteTCP {
    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;

    public ClienteTCP(String servidor, int puerto) throws IOException {
        socket = new Socket(servidor, puerto);
        salida = new PrintWriter(socket.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void enviarMensaje(String mensaje) {
        salida.println(mensaje);
    }

    public String recibirMensaje() throws IOException {
        return entrada.readLine();
    }

    public void cerrar() throws IOException {
        salida.close();
        entrada.close();
        socket.close();
    }

    public static void main(String[] args) {
        String servidor = "localhost"; // Cambia esta dirección según la ubicación de tu servidor
        int puerto = 1234; // Cambia este puerto según tus necesidades
        try {
            ClienteTCP cliente = new ClienteTCP(servidor, puerto);
            
            // Ejemplo de envío de mensaje al servidor
            cliente.enviarMensaje("Hola, servidor!");
            
            // Ejemplo de recepción de mensaje del servidor
            String respuesta = cliente.recibirMensaje();
            System.out.println("Respuesta del servidor: " + respuesta);
            
            cliente.cerrar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
