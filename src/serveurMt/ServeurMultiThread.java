package serveurMt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurMultiThread {

    public static void main(String[] args) {
        int port = 1234;
        int clientCount = 0; // compteur de clients

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Le serveur attend les clients ...");

            while (true) {
                // Acceptation d'un client
                Socket clientSocket = serverSocket.accept();
                clientCount++;

                // Affichage des propriétés du client
                System.out.println("Client #" + clientCount + " connecté depuis " 
                                   + clientSocket.getRemoteSocketAddress());

                // Création et démarrage du thread pour ce client
                ClientProcess clientThread = new ClientProcess(clientSocket, clientCount);
                clientThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
