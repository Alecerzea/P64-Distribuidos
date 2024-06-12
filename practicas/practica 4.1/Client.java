import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Client {
    private static final String HOST = "localhost"; 
    private static final int PORT = 8080; 

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            MyObject myObject = new MyObject("Cliente Objeto", 123); 
            oos.writeObject(myObject); 
            oos.flush();

            List<MyObject> receivedObjects = (List<MyObject>) ois.readObject(); 
            System.out.println("Objetos recibidos: " + receivedObjects);

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al conectar al servidor: " + e.getMessage());
        }
    }
}

//Byron Sellan Soto