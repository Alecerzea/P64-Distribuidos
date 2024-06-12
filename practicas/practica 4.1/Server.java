import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 8080; // Puerto del servidor
    private static final List<MyObject> objectList = new ArrayList<>(); // Colección de objetos

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10); // Pool de hilos para manejar clientes

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Acepta conexiones de clientes
                executor.execute(new ClientHandler(clientSocket)); // Crea un hilo para manejar al cliente
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown(); // Cierra el pool de hilos
        }
    }

    // Clase interna para manejar las conexiones de clientes en hilos separados
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                 ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {

                // Recibe el objeto del cliente
                MyObject receivedObject = (MyObject) ois.readObject();
                System.out.println("Recibido objeto: " + receivedObject);

                // Agrega el objeto a la colección
                objectList.add(receivedObject);

                // Envía la lista de objetos al cliente
                oos.writeObject(objectList);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close(); 
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


class MyObject implements java.io.Serializable {
    private String name;
    private int id;

    public MyObject(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
//Byron Sellan Soto