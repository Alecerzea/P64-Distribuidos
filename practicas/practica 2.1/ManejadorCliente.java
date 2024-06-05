import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ManejadorCliente extends Thread {
    private Socket clienteSocket;
    private ReentrantLock lock;
    private Condition condition;

    public ManejadorCliente(Socket clienteSocket, ReentrantLock lock, Condition condition) {
        this.clienteSocket = clienteSocket;
        this.lock = lock;
        this.condition = condition;
    }

    public ManejadorCliente(Socket cliente) {
        //TODO Auto-generated constructor stub
    }

    public void run() {
        try (
            PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()))
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                lock.lock();
                try {
                    System.out.println("Mensaje recibido de " + clienteSocket.getInetAddress().getHostAddress() + ": " + inputLine);
                    out.println("Mensaje recibido: " + inputLine);
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
            clienteSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

