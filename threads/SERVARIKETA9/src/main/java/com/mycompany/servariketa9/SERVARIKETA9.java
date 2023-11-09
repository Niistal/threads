/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.servariketa9;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author iker
 */
public class SERVARIKETA9 {

    public static void main(String[] args) throws InterruptedException {
        // Receives the number of parking spaces and the number of existing cars in the
        // system
        int numCars = 10;

        ParkingLot parkingLot = new ParkingLot(6);
        Cars[] cars = new Cars[numCars];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Cars(parkingLot, "Car " + (i + 1));
        }

        for (Cars car : cars) {
            car.start();
            // car.join();
            Thread.sleep(new Random().nextInt(3000));
        }
    }
}

class Cars extends Thread {
    private ParkingLot parkingLot;
    private String id;

    public Cars(ParkingLot parkingLot, String id) {
        this.parkingLot = parkingLot;
        this.id = id;
    }

    public void run() {
        try {
            while (true) {
                parkingLot.vehicleEntry(id);
                Thread.sleep(new Random().nextInt(3000));
                parkingLot.vehicleExit(id);
                Thread.sleep(new Random().nextInt(3000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ParkingLot {
    private Semaphore semaphore;
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        semaphore = new Semaphore(capacity);
    }

    public void parking() {
        System.out.print("Parking: ");
        for (int i = 0; i < capacity; i++) {
            if (i < capacity - semaphore.availablePermits()) {
                System.out.print("[" + (i + 1) + "]");
            } else {
                System.out.print("[0]");
            }
        }
        System.out.println();
    }

    public synchronized void vehicleEntry(String id) throws InterruptedException {
        semaphore.acquire();
        System.out.println();
        System.out.println("ENTRANCE: " + id + " parks in spot " + (capacity - semaphore.availablePermits()));
        System.out.println("Available spaces: " + semaphore.availablePermits());
        parking();
        Thread.sleep(new Random().nextInt(3000));
    }

    public synchronized void vehicleExit(String id) throws InterruptedException {
        System.out.println();
        System.out.println("EXIT: " + id + " exiting.");
        semaphore.release();
        System.out.println("Available spaces: " + semaphore.availablePermits());
        parking();
        Thread.sleep(new Random().nextInt(3000));
    }

}

