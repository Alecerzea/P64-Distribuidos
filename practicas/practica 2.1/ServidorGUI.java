import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;

public class ServidorGUI {

    private JFrame frame;
    private JTextArea mensajesArea;
    private JTextField mensajeField;
    private JButton iniciarButton;
    private JButton enviarButton;
    private JLabel connectionStatus;
    private JButton stopButton;
    private ServerSocket serverSocket;
    private PrintWriter out_socket;
    private BufferedReader in_socket;
    private Socket socket;

    public ServidorGUI() {
        frame = new JFrame("Servidor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        mensajesArea = new JTextArea();
        mensajesArea.setEditable(false);
        mensajeField = new JTextField(20);
        iniciarButton = new JButton("Iniciar Servidor");
        enviarButton = new JButton("Enviar");
        connectionStatus = new JLabel("Desconectado");
        stopButton = new JButton("Detener Servidor");

        iniciarButton.addActionListener(e -> new Thread(this::conectar).start());
        enviarButton.addActionListener(e -> enviarMensaje(mensajeField.getText()));
        stopButton.addActionListener(e -> stopServer());

        JPanel panel = new JPanel();
        panel.add(iniciarButton);
        panel.add(stopButton);

        JPanel mensajePanel = new JPanel();
        mensajePanel.add(new JLabel("Mensaje:"));
        mensajePanel.add(mensajeField);
        mensajePanel.add(enviarButton);

        JPanel statusPanel = new JPanel();
        statusPanel.add(connectionStatus);

        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.getContentPane().add(panel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.getContentPane().add(new JScrollPane(mensajesArea), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.getContentPane().add(mensajePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.getContentPane().add(statusPanel, gbc);

        frame.setVisible(true);
    }

    public void conectar() {
        try {
            serverSocket = new ServerSocket(2020);
            mensajesArea.append("Puerto 2020 se encuentra abierto.\n");
            connectionStatus.setText("Conectado");

            socket = serverSocket.accept();
            InputStreamReader in_reader = new InputStreamReader(socket.getInputStream());
            in_socket = new BufferedReader(in_reader);
            OutputStreamWriter out_writer = new OutputStreamWriter(socket.getOutputStream());
            out_socket = new PrintWriter(out_writer, true);

            mensajesArea.append("Cliente " + socket.getInetAddress() + " se ha conectado.\n");

            new Thread(() -> {
                try {
                    String mensaje;
                    while ((mensaje = in_socket.readLine()) != null) {
                        mensajesArea.append("Cliente: " + mensaje + "\n");
                    }
                } catch (IOException e) {
                    mensajesArea.append("Error en la comunicación con el cliente.\n");
                }
            }).start();

        } catch (IOException e) {
            mensajesArea.append("No se pudo abrir el puerto 2020.\n");
            e.printStackTrace();
        }
    }

    public void enviarMensaje(String mensaje) {
        if (out_socket != null) {
            out_socket.println(mensaje);
            mensajesArea.append("Servidor: " + mensaje + "\n");
            mensajeField.setText("");
        }
    }

    public void stopServer() {
        try {
            serverSocket.close();
            connectionStatus.setText("Desconectado");
            mensajesArea.append("Servidor detenido.\n");
        } catch (IOException e) {
            mensajesArea.append("Error al detener el servidor.\n");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ServidorGUI();
    }
}