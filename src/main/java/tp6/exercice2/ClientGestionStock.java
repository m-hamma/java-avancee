package com.hamma.tp6.exercice2;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientGestionStock {
    final static int port = 9632;

    public static void main(String[] args) {

        // Serveur Host
        final String serverHost = "localhost";
        final Scanner sc = new Scanner(System.in);
        try {

            // envoyer une requete au serveur qui ecoute
            // sur 'localhost' port 9632.
            final Socket socketOfClient = new Socket(serverHost, port);

            // Créee output stream au client (envoyer les données au serveur)
            PrintStream os = new PrintStream(socketOfClient.getOutputStream());

            // Recevoir.
            final BufferedReader is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

            Thread recevoir = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // Lire les données qui proviennent du serveur.
                        String responseLine;
                        while (true) {
                            responseLine = is.readLine();
                            if(responseLine!=null) {
                                System.out.println("Server: " + responseLine);
                                if (responseLine != null && responseLine.toString().equals("QUIT")) {
                                    break;
                                }
                            }
                        }

                        os.close();
                        is.close();
                        socketOfClient.close();
                    } catch (UnknownHostException e) {
                        System.err.println("Trying to connect to unknown host: " + e);
                    } catch (IOException e) {
                        System.err.println("IOException:  " + e);
                    }
                }
            });
            recevoir.start();
            Thread envoyer = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        // ecrire dans  Client Socket.
                        String msg;
                        while (true) {
                            msg = sc.nextLine();
                            os.println(msg);
                            os.flush();
                            if (msg.equals("ADD")) {
                                String medicament = sc.nextLine();
                                os.println(medicament);
                                os.flush();
                            }
                            if (msg.equals("exit")) {
                                os.println("déconnexion");
                                os.flush();
                                break;
                            }
                        }

                        os.close();
                        is.close();
                        socketOfClient.close();
                    } catch (UnknownHostException e) {
                        System.err.println("Trying to connect to unknown host: " + e);
                    } catch (IOException e) {
                        System.err.println("IOException:  " + e);
                    }
                }
            });
            envoyer.start();
        } catch (UnknownHostException e) {
            System.err.println("Serveur inconnu " + serverHost);
            return;
        } catch (IOException e) {
            System.err.println("Erreur connection  : " + serverHost);
            return;
        }
    }
}