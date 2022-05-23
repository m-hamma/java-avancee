package com.hamma.tp6.exercice1.recevoir;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EtudiantRecevoir implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    String pseudo;

    public String getPseudo() {
        return pseudo;
    }

    public EtudiantRecevoir(Socket clientSocket, BufferedReader in, PrintWriter out) {
        this.clientSocket = clientSocket;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        String msg;
        try {
            msg = in.readLine();
            int compteur = 0;
            while (msg != null) {
                System.out.println("Instituteur : " + msg);
                msg = in.readLine();
                if (compteur == 0) {
                    pseudo = msg;
                }
                compteur++;
            }
            System.out.println("Serveur déconecté");
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
