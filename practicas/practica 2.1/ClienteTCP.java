import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteTCP {
    private static final String SERVIDOR_IP = "localhost";
    private static final int PUERTO = 22;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVIDOR_IP, PUERTO);
            System.out.println("Conectado al servidor en " + SERVIDOR_IP + ":" + PUERTO);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese su nombre: ");
            String nombreCliente = scanner.nextLine();

            ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
            salida.writeObject(new CustomObject(nombreCliente, ""));

            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

            while (true) {
                System.out.print("Ingrese un mensaje: ");
                String mensaje = scanner.nextLine();
                salida.writeObject(new CustomObject(nombreCliente, mensaje));

                CustomObject objetoRecibido = (CustomObject) entrada.readObject();
                System.out.println("Servidor: " + objetoRecibido.getMessage());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}