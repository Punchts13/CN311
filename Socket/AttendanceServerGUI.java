import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AttendanceServerGUI extends JFrame {
    private JTextArea statusTextArea;

    public AttendanceServerGUI() {
        setTitle("Server Status");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        statusTextArea = new JTextArea();
        statusTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statusTextArea);
        add(scrollPane);

        setVisible(true);

        // Start the server
        startServer();
    }

    private void startServer() {
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    ServerSocket serverSocket = new ServerSocket(9999);
                    publish("Server started. Listening on port 9999...");

                    while (true) {
                        Socket clientSocket = serverSocket.accept();
                        publish("Accepted connection from " + clientSocket.getRemoteSocketAddress());
                        clientSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                for (String message : chunks) {
                    statusTextArea.append(message + "\n");
                }
            }
        };

        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AttendanceServerGUI());
    }
}
