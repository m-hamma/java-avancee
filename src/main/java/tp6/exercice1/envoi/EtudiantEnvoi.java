package com.hamma.tp6.exercice1.envoi;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EtudiantEnvoi implements Runnable{
    private Socket clientSocket ;
    private BufferedReader in;
    private PrintWriter out;
    private Scanner sc;
    private String pseudo;

    public EtudiantEnvoi(Socket clientSocket, BufferedReader in, PrintWriter out, Scanner sc,String pseudo) {
        this.clientSocket = clientSocket;
        this.in = in;
        this.out = out;
        this.sc = sc;
        this.pseudo=pseudo;
    }

    @Override
    public void run() {
        String msg ;
        this.pseudo = sc.nextLine();
        out.println("je suis ->" + this.pseudo);
        out.flush();
        while (true) {
            msg = sc.nextLine();
            out.println("@"+this.pseudo+"@->" + msg);
            out.flush();
            if (msg.equals("exit")) {
                out.println("@"+this.pseudo+"@->déconnexion" );
                out.flush();
                break;
            }
        }
    }
}
