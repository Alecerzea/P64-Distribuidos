import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.*;

public class ServidorTCP {
    private static final int PUERTO = 2024;
    private static List<PrintWriter> salidasClientes = new ArrayList<>();
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

                PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                String nombreCliente = entrada.readLine(); // Leer nombre del cliente
                System.out.println("Cliente " + nombreCliente + " conectado desde: " + cliente.getInetAddress());

                lock.lock();
                try {
                    salidasClientes.add(salida);
                } finally {
                    lock.unlock();
                }

                // Iniciar un hilo para manejar la comunicación con el cliente
                Thread thread = new Thread(new ManejadorCliente(cliente, salida));
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
        private PrintWriter salida;
        private BufferedReader entrada;
        private String nombreCliente;

        public ManejadorCliente(Socket cliente, PrintWriter salida) {
            this.cliente = cliente;
            this.salida = salida;
            try {
                this.entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                this.nombreCliente = entrada.readLine(); // Leer nombre del cliente
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    System.out.println("Mensaje recibido de " + nombreCliente + ": " + mensaje);

                    // Enviar respuesta al cliente
                    String respuesta = "Respuesta del servidor a " + nombreCliente + ": " + mensaje;
                    salida.println(respuesta);

                    // Enviar el mensaje a todos los clientes
                    lock.lock();
                    try {
                        for (PrintWriter salidaCliente : salidasClientes) {
                            if (salidaCliente != salida) {
                                salidaCliente.println(nombreCliente + ": " + mensaje);
                            }
                        }
                    } finally {
                        lock.unlock();
                    }
                }

                // Cerrar la conexión cuando el cliente se desconecta
                lock.lock();
                try {
                    salidasClientes.remove(salida);
                } finally {
                    lock.unlock();
                }
                cliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

