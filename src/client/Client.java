package client;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Connexion au serveur (local ou réseau)
            Socket socket = new Socket("localhost", 1234);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            // Affiche le message d'accueil (numéro du client)
            System.out.println(in.readLine());

            String message;
            while ((message = keyboard.readLine()) != null) {
                out.println(message);
                System.out.println(in.readLine());
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
