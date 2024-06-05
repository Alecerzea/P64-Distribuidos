import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    private ServerSocket serverSocket;

    public ServidorTCP(int puerto) throws IOException {
        serverSocket = new ServerSocket(puerto);
        System.out.println("Servidor TCP iniciado en el puerto " + puerto);
    }

    public void aceptarClientes() {
        while (true) {
            try {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado: " + clienteSocket.getInetAddress().getHostAddress());

                // Aquí puedes manejar la comunicación con el cliente en un hilo separado
                Thread clienteThread = new Thread(new ManejadorCliente(clienteSocket));
                clienteThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int puerto = 1234; // Cambia este puerto según tus necesidades
        try {
            ServidorTCP servidor = new ServidorTCP(puerto);
            servidor.aceptarClientes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

