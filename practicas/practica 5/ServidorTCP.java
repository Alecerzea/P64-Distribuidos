import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) {
        final int PUERTO = 22;
        ServerSocket servidor = null;

        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor TCP iniciado en el puerto " + PUERTO);

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado desde " + cliente.getInetAddress().getHostAddress());
                ManejadorCliente manejador = new ManejadorCliente(cliente);
                manejador.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (servidor != null) {
                try {
                    servidor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
