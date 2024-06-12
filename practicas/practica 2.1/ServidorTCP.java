import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.*;

public class ServidorTCP {
    private static final int PUERTO = 22;
    private static List<ObjectOutputStream> salidasClientes = new ArrayList<>();
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition noMensajes = lock.newCondition();

    public static void main(String[] args) {
        ServerSocket servidor = null;

        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor TCP iniciado en el puerto " + PUERTO);

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado desde: " + cliente.getInetAddress());

                ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());

                lock.lock();
                try {
                    salidasClientes.add(salida);
                } finally {
                    lock.unlock();
                }

                // Iniciar un hilo para manejar la comunicación con el cliente
                Thread thread = new Thread(new ManejadorCliente(cliente, salida, entrada));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (servidor != null) {
                    servidor.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class ManejadorCliente implements Runnable {
        private Socket cliente;
        private ObjectOutputStream salida;
        private ObjectInputStream entrada;

        public ManejadorCliente(Socket cliente, ObjectOutputStream salida, ObjectInputStream entrada) {
            this.cliente = cliente;
            this.salida = salida;
            this.entrada = entrada;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    CustomObject objetoRecibido = (CustomObject) entrada.readObject();
                    System.out.println("Mensaje recibido de " + objetoRecibido.getName() + ": " + objetoRecibido.getMessage());

                    // Enviar respuesta al cliente
                    CustomObject objetoEnviar = new CustomObject("Servidor", "Respuesta del servidor a " + objetoRecibido.getName() + ": " + objetoRecibido.getMessage());
                    salida.writeObject(objetoEnviar);

                    // Enviar el mensaje a todos los clientes
                    lock.lock();
                    try {
                        for (ObjectOutputStream salidaCliente : salidasClientes) {
                            if (salidaCliente != salida) {
                                salidaCliente.writeObject(objetoEnviar);
                            }
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}