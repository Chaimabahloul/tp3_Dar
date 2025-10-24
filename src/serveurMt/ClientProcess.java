package serveurMt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientProcess extends Thread {

    private Socket socket;
    private int clientNumber;

    public ClientProcess(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // Envoi au client de son ordre de communication
            out.println("Bienvenue ! Vous êtes le client #" + clientNumber);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Message reçu du client " + clientNumber + ": " + message);
                out.println("Echo: " + message);
            }

            socket.close();
            System.out.println("Client #" + clientNumber + " déconnecté.");

        } catch (IOException e) {
            System.out.println("Erreur avec le client #" + clientNumber + ": " + e.getMessage());
        }
    }
}
