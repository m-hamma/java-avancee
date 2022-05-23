package com.hamma.tp6.exercice1.envoi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class InstEnvoi implements Runnable {
    private Socket clientSocket;
    private ServerSocket serveurSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Scanner sc;

    public InstEnvoi(ServerSocket serveurSocket, Socket clientSocket, BufferedReader in, PrintWriter out, Scanner sc) {
        this.serveurSocket = serveurSocket;
        this.in = in;
        this.out = out;
        this.sc = sc;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        String msg;
        out.println("c'est quoi votre pseudo svp > ");
        out.flush();

        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
                true);
             BufferedReader in = new BufferedReader(new InputStreamReader(
                     clientSocket.getInputStream()))) {

            while (true) {
                msg = sc.nextLine();
                out.println("@all@" + msg);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}