import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private ServerSocket serverSocket;
    private List<Persona> personas = new ArrayList<>();

    public Servidor(int puerto) throws IOException {
        serverSocket = new ServerSocket(puerto);
        System.out.println("Servidor iniciado en el puerto " + puerto);
    }

    public void start() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Conexión establecida con un cliente");
                ClienteHilo clienteHilo = new ClienteHilo(socket);
                clienteHilo.start();
            } catch (IOException e) {
                System.err.println("Error al aceptar conexión: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            Servidor servidor = new Servidor(8080);
            servidor.start();
        } catch (IOException e) {
            System.err.println("Error al iniciar servidor: " + e.getMessage());
        }
    }

    private class ClienteHilo extends Thread {
        private Socket socket;

        public ClienteHilo(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Persona persona = (Persona) objectInputStream.readObject();
                personas.add(persona);
                System.out.println("Persona agregada: " + persona);

                // Enviar la lista de personas al cliente
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(personas);

                socket.close();
            } catch (IOException e) {
                System.err.println("Error al procesar cliente: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.err.println("Error al deserializar objeto: " + e.getMessage());
            }
        }
    }
}