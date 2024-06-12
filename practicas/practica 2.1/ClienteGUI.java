import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteGUI {

    private JFrame frame;
    private JTextField ipField;
    private JTextField puertoField;
    private JTextField mensajeField;
    private JTextArea mensajesArea;
    private PrintWriter out_socket;
    private BufferedReader in_socket;

    public ClienteGUI() {
        frame = new JFrame("Cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        ipField = new JTextField(15);
        puertoField = new JTextField(5);
        mensajeField = new JTextField(20);
        JButton conectarButton = new JButton("Conectar");
        JButton enviarButton = new JButton("Enviar");
        mensajesArea = new JTextArea();
        mensajesArea.setEditable(false);

        JPanel panel = new JPanel();
        panel.add(new JLabel("IP:"));
        panel.add(ipField);
        panel.add(new JLabel("Puerto:"));
        panel.add(puertoField);
        panel.add(conectarButton);

        JPanel mensajePanel = new JPanel();
        mensajePanel.add(new JLabel("Mensaje:"));
        mensajePanel.add(mensajeField);
        mensajePanel.add(enviarButton);

        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(mensajesArea));
        frame.getContentPane().add(BorderLayout.SOUTH, mensajePanel);

        conectarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ip = ipField.getText();
                int puerto = Integer.parseInt(puertoField.getText());
                conectar(ip, puerto);
            }
        });

        enviarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enviarMensaje(mensajeField.getText());
            }
        });

        frame.setVisible(true);
    }

    public void conectar(String ip, int puerto) {
        try {
            Socket socket = new Socket(ip, puerto);
            InputStreamReader in_reader = new InputStreamReader(socket.getInputStream());
            in_socket = new BufferedReader(in_reader);
            OutputStreamWriter out_writer = new OutputStreamWriter(socket.getOutputStream());
            out_socket = new PrintWriter(out_writer, true);

            mensajesArea.append("Conexión exitosa con el servidor.\n");

            new Thread(() -> {
                try {
                    String mensaje;
                    while ((mensaje = in_socket.readLine()) != null) {
                        mensajesArea.append("Servidor: " + mensaje + "\n");
                    }
                } catch (IOException e) {
                    mensajesArea.append("Error en la comunicación con el servidor.\n");
                }
            }).start();

        } catch (UnknownHostException e) {
            mensajesArea.append("Host desconocido: " + ip + "\n");
            e.printStackTrace();
        } catch (IOException e) {
            mensajesArea.append("Error de I/O al conectar con: " + ip + ":" + puerto + "\n");
            e.printStackTrace();
        }
    }

    public void enviarMensaje(String mensaje) {
        if (out_socket != null) {
            out_socket.println(mensaje);
            mensajesArea.append("Cliente: " + mensaje + "\n");
            mensajeField.setText("");
        }
    }

    public static void main(String[] args) {
        new ClienteGUI();
    }
}