/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.servariketa8;

import java.util.concurrent.Semaphore;

/**
 *
 * @author iker
 */
public class SERVARIKETA8 {

    public static void main(String[] args) throws InterruptedException {

        
        Relay[] relays = new Relay[4];
        Semaphore semaphore = new Semaphore(1);

        for (int i = 0; i < relays.length; i++) {
            relays[i] = new Relay(semaphore, i);
        }
        System.out.println("All threads created.");
        System.out.println("Ready, set, go!");
        
        for (Relay relay : relays) {
            relay.start();
            relay.join();
        }
        System.out.println("All threads have finished.");

    }

}

class Relay extends Thread {
    private Semaphore semaphore;
    private int i;

    public Relay(Semaphore semaphore, int i) {
        this.i = i;
        this.semaphore = semaphore;

    }

    public void run() {
        try {
            semaphore.acquire(); // Request a permit
            System.out.println("I'm thread " + (i + 1) + ", running...");
            Thread.sleep(2000);
            if(i!=3){
            System.out.println("I'm done passing the baton. to child " + (i + 2 )+ ".");
            semaphore.release(); // Release the permi
        } else {
    System.out.println("I´m done");
}
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

    }

}

    







