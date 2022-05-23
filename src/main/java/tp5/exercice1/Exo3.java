package com.hamma.exercice1;

public class Exo3 {
    public static void main(String[] args) {
        Exo2 exo2=new Exo2();
        Thread thread1 = new Thread(exo2);
        thread1.start();
        Thread thread2 = new Thread(exo2);
        thread2.start();
        Thread thread3 = new Thread(exo2);
        thread3.start();
    }
}
