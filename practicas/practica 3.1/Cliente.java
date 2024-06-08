import java.io.*;
import java.net.*;

public class Cliente {
    private Socket socket;

    public Cliente(String host, int puerto) throws IOException {
        socket = new Socket(host, puerto);
    }

    public void enviarPersona(Persona persona) throws IOException {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(persona);

            // Recibir la lista de personas del servidor
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            List<Persona> personas = (List<Persona>) objectInputStream.readObject();
            System.out.println("Personas recibidas del servidor:");
            for (Persona p : personas) {
                System.out.println(p);
            }
        } catch (IOException e) {
            System.err.println("Error al enviar persona: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error al deserializar objeto: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Cliente cliente = new Cliente("localhost", 8080);
            Persona persona1 = new Persona("Juan", 25);
            cliente.enviarPersona(persona1);

            Persona persona2 = new Persona("María", 30);
            cliente.enviarPersona(persona2);
        } catch (IOException e) {
            System.err.println("Error al conectar con servidor: " + e.getMessage());
        }
    }
}