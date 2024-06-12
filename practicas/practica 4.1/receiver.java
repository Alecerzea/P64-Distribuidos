import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Receiver {

    public static final int PORT = 2020;
    public static final int BUFFER_SIZE = 1500;

    public Receiver() {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            System.out.println("Receiver se encuentra funcionando.");

            while (true) {
                // Recibir solicitud
                byte[] buffer = new byte[BUFFER_SIZE];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String mensaje = new String(buffer, 0, packet.getLength());
                System.out.println("Recibido: " + mensaje);

                // Enviar respuesta
                InetAddress sender_address = packet.getAddress();
                int sender_port = packet.getPort();

                System.out.print("Responder: ");
                mensaje = new Scanner(System.in).nextLine();

                buffer = mensaje.getBytes();
                packet = new DatagramPacket(buffer, buffer.length, sender_address, sender_port);
                socket.send(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Receiver objeto = new Receiver();
    }
}