package com.hamma.tp6.exercice1;

import com.hamma.tp6.exercice1.envoi.InstEnvoi;
import com.hamma.tp6.exercice1.recevoir.InstRecevoir;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class InstServeur {
    public static void main(String[] args) {
        final ServerSocket serveurSocket;
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc = new Scanner(System.in);

        try {
            serveurSocket = new ServerSocket(5000);
            clientSocket = serveurSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            InstEnvoi instEnvoi = new InstEnvoi(serveurSocket,clientSocket,
                    in,
                    out,sc);
            Thread envoi = new Thread(instEnvoi);
            envoi.start();

            InstRecevoir instRecevoir = new InstRecevoir(serveurSocket,
                    clientSocket,
                    in,
                    out);
            Thread recevoir = new Thread(instRecevoir);
            recevoir.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
