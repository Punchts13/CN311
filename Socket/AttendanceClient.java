import java.io.*;
import java.net.*;

public class AttendanceClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter your name for attendance: ");
            String name = stdIn.readLine();
            out.println(name);

            String response = in.readLine();
            System.out.println("Server response: " + response);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
