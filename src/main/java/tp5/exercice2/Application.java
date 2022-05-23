package com.hamma.exercice2;

public class Application {
    private static boolean listNonInterrupt = true;

    public static void main(String[] args) {

        Thread patience = new Thread(() -> {
            int compteurPing=5;
            int compteurPong=5;
            while (listInterrupeted()) {

                Thread thr = Thread.currentThread();
                System.out.println("-----Thrad"+thr.toString());
                if(thr.isAlive() && compteurPong==0) {
                    compteurPing++;
                    System.out.println("ping");
                    if(compteurPing==5){
                        compteurPong=5;
                    }
                }

                try {
                    Thread.sleep(2000);
                    if(compteurPing==5) {
                        compteurPong--;
                        System.out.println("pong");
                        if(compteurPong==0){
                            compteurPing=0;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        patience.start();
        patience.interrupt();
        //
        try {
            //listNonInterrupt = false;
            patience.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean listInterrupeted() {
        return listNonInterrupt;
    }
}
