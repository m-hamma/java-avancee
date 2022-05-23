package com.hamma.tp6.exercice1;

import com.hamma.tp6.exercice1.envoi.EtudiantEnvoi;
import com.hamma.tp6.exercice1.recevoir.EtudiantRecevoir;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Etudiant {

    public static void main(String[] args) {
        String pseudo="";
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc = new Scanner(System.in);//pour lire à partir du clavier

        try {
            clientSocket = new Socket("127.0.0.1", 5000);
            //flux pour envoyer
            out = new PrintWriter(clientSocket.getOutputStream());
            //flux pour recevoir
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            /*
             * les informations du serveur ( port et adresse IP ou nom d'hote
             * 127.0.0.1 est l'adresse local de la machine
             */
            EtudiantRecevoir etudiantRecevoir = new EtudiantRecevoir(
                    clientSocket,
                    in,
                    out);
            Thread recevoir = new Thread(etudiantRecevoir);
            recevoir.start();

            EtudiantEnvoi etudiantEnvoi = new EtudiantEnvoi(
                    clientSocket,
                    in,
                    out,sc,etudiantRecevoir.getPseudo());
            Thread envoyer = new Thread(etudiantEnvoi);
            envoyer.start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
