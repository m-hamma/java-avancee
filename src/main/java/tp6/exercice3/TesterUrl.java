package com.hamma.tp6.exercice3;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.net.http.WebSocket;
import java.util.Scanner;

public class TesterUrl {
    static final File file = new File("tests-url.txt");
    public static void main(String[] args) throws MalformedURLException {
        System.out.println("Veuillez taper une URL svp (exemple www.google.fr): ");
        final Scanner sc = new Scanner(System.in);
        String urlString = sc.nextLine();
        URL url = new URL("http://" + urlString + "/");
        //protocoles
        testerURL("https://" + url);
        testerURL("ftp://" + url);
        testerURL("news://" + url);
        testerURL("file://" + url);
        testerURL("gopher://" + url);
        testerURL("ldap://" + url);
        //port par défaut
        System.out.println("port par défaut : "+url.getDefaultPort());
        //domaine
        System.out.println("Domaine de l'url : "+url.getAuthority());
        for (int i = 1; i <= 80; i++) {
            Thread tests = new Thread(() -> {
                //ports
                for (int port=8080;port<8099;port++) {
                    try {
                        Socket connexion = new Socket(urlString, port);
                        System.out.println("port "+port+" -> OK");
                    } catch (MalformedURLException e) {
                        System.out.println("port "+port+" -> KO");
                    } catch (UnknownHostException e) {
                        System.out.println("Erreur UnknownHostException");
                    } catch (IOException e) {
                        System.out.println("Erreur IOException");
                    }
                }
                //Lecture du contenu
                InputStream in = null;
                try {
                    in = url.openStream();
                } catch (IOException e) {
                    System.out.println("Erreur ouverture de flux");
                }
                int c;
                FileWriter fw = null;
                try {
                    fw = new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    while (true) {
                        try {
                            if (in!=null && !((c = in.read()) != -1))
                                bw.write(c);
                            else
                                break;
                        } catch (IOException e) {
                            System.out.println("Erreur lecture données");
                        }

                    }
                } catch (IOException e) {
                    System.out.println("Erreur création fichier");
                }

            });
            tests.start();
        }
    }

    static void testerURL(String url) {
        try {
            URL uneURL = new URL(url);
            System.out.println("Protocole supporté : " + uneURL.getProtocol());
        } catch (MalformedURLException e) {
            System.out.println("Problème avec cette URL");
            System.out.println("Protocole non supporté : " + url.substring(0, url.indexOf(":")));
        }

    }
}
