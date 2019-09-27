package ru.nsu.ccfit.voytenko.factory;

import java.lang.*;

public class Dealer implements Runnable {

    public int dealerId;
    private static int numDealers = 0;
    private static volatile int alreadySale = 0;
    private Storage workingStorage;
    public static final int minSpeed = 1;
    public static final int maxSpeed = 11;
    private static int avgSpeed = (minSpeed + maxSpeed) / 2;

    public Dealer(Storage autoStorage){
        this.workingStorage = autoStorage;
        dealerId = numDealers;
        numDealers++;
    }

    @Override
    public void run() {
        while (true){
            sale();
            try{
                Thread.sleep(1000 / avgSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sale(){
        try {
            Detail autoForSale = workingStorage.getDetail();
            alreadySale++;
            //System.out.println(autoForSale.getType() + " was saled, count = " + alreadySale);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setDealerSpeed(int speed){
        avgSpeed = speed;
    }

    public String getSpeed(){
        return Integer.toString(avgSpeed);
    }

    public int getSaledCount(){
        return alreadySale;
    }

}
