package ru.nsu.ccfit.voytenko.factory;


import java.lang.*;

public class WorkerTask implements Runnable{

    Detail motor;
    Detail body;
    Detail accesories;
    Storage autoStorage;

    WorkerTask(Detail m, Detail b, Detail ac, Storage st){
        motor = m;
        body = b;
        accesories = ac;
        autoStorage = st;
    }

    public Detail getMotor(){
        return motor;
    }

    public Detail getBody(){
        return body;
    }

    public Detail getAccesories(){
        return accesories;
    }

    public void createAuto() throws InterruptedException {
        int autoId = autoStorage.getStorageSize();
        //System.out.println("Current auto id: " + autoId);
        autoStorage.putDetail(new Auto(autoId, getMotor(), getAccesories(), getBody()));
    }

    @Override
    public void run() {
        try {
            createAuto();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
