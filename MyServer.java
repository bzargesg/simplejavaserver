import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;;
import java.util.Scanner;

public class MyServer {
  public static void main(String[] args) {
    connectToServer();
  }

  public static void connectToServer() {
    // Try connect to the server on an unused port. succesful connection returns a
    // socket
    try (ServerSocket serverSocket = new ServerSocket(9991)) {
      Socket connectionSocket = serverSocket.accept();

      // Make input outputstreams for the connection
      InputStream inputToServer = connectionSocket.getInputStream();
      OutputStream outputFromServer = connectionSocket.getOutputStream();

      Scanner scanner = new Scanner(inputToServer, "UTF-8");
      PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(outputFromServer, "UTF-8"), true);

      serverPrintOut.println("Words! enter googly to exit.");

      // input
      boolean done = false;

      while (!done && scanner.hasNextLine()) {
        String line = scanner.nextLine();
        serverPrintOut.println("Echo from Server: " + line);

        if (line.toLowerCase().trim().equals("googly")) {
          done = true;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}