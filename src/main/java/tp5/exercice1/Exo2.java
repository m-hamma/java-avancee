package com.hamma.exercice1;

public class Exo2 implements Runnable {
    @Override
    public void run() {
        Exo1 exo1 = new Exo1();
        while (true) {
            exo1.testModif();
        }
    }
}
