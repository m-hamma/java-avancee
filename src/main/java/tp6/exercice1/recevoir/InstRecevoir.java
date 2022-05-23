package com.hamma.tp6.exercice1.recevoir;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class InstRecevoir implements Runnable {
    private ServerSocket serveurSocket  ;
    private Socket clientSocket ;
    private BufferedReader in;
    private PrintWriter out;

    public InstRecevoir(ServerSocket serveurSocket, Socket clientSocket, BufferedReader in, PrintWriter out) {
        this.serveurSocket = serveurSocket;
        this.clientSocket = clientSocket;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        String msg ;
        try {
            msg = in.readLine();
            //tant que l'étudiant est connecté
            while(msg!=null){
                System.out.println(msg);
                msg = in.readLine();
                if("Exit".equals(msg)) {
                    break;
                }
            }
            //sortir de la boucle si le client a déconecté
            System.out.println("déconnexion de l'apprenant");
            //fermer le flux et la session socket
            out.close();
            clientSocket.close();
            serveurSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
