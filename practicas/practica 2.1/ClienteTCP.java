import java.io.*;
import java.net.*;

public class ClienteTCP {
    private static final String SERVIDOR_IP = "localhost";
    private static final int PUERTO = 2024;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVIDOR_IP, PUERTO);
            System.out.println("Conectado al servidor en " + SERVIDOR_IP + ":" + PUERTO);

            BufferedReader entradaServidor = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salidaCliente = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entradaConsola = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Ingrese su nombre: ");
            String nombreCliente = entradaConsola.readLine();
            salidaCliente.println(nombreCliente); // Enviamos el nombre al servidor

            // Hilo para recibir mensajes del servidor
            Thread recibirHilo = new Thread(() -> {
                try {
                    String mensajeServidor;
                    while ((mensajeServidor = entradaServidor.readLine()) != null) {
                        System.out.println("Servidor: " + mensajeServidor);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            recibirHilo.start();

            // Leer mensajes desde la consola y enviarlos al servidor
            String mensajeConsola;
            while ((mensajeConsola = entradaConsola.readLine()) != null) {
                salidaCliente.println(mensajeConsola);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
