package com.hamma.exercice1;

import java.io.File;

public class Exo1 {
    private int i=1;
    public synchronized void testModif() {
        System.out.println(i);
        i=1;
        System.out.println("Début création fichier");
        File f = new File("D:\\exo2.txt");
        System.out.println("Début suppression fichier");
        f.delete();
        System.out.println("Fin suppression fichier");
        i=i-1;
    }
}
