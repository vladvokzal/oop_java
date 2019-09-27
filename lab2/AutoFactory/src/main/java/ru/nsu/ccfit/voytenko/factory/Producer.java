package ru.nsu.ccfit.voytenko.factory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Producer implements Runnable, Subscriber {

    Storage someStorage;
    String detailType;
    boolean amIAloneProducer;
    private int producerId;
    public static final int minSpeed = 1;
    public static final int maxSpeed = 11;
    private double avgSpeed = (minSpeed + maxSpeed) / 2;

    Producer(Storage someStorage, String detailType, boolean amIAloneProducer, int prodId){
        this.someStorage = someStorage;
        this.detailType = detailType;
        this.amIAloneProducer = amIAloneProducer;
        this.producerId = prodId;
    }


    synchronized public void putDetail(){
        Class<?> c;
        Object o;
        Field f;
        try {
            c = Class.forName("ru.nsu.ccfit.voytenko.factory." + detailType);
            f = c.getDeclaredField("id");
            f.setAccessible(true);
            if(amIAloneProducer){
                for (int i = 0; i < someStorage.getStorageCapacity(); i++){
                    o = c.getDeclaredConstructor().newInstance();
                    Detail tmp = (Detail)o;
                    f.set(tmp, producerId++);
                    someStorage.putDetail(tmp);
                }
            }
            else {
                o = c.getDeclaredConstructor().newInstance();
                Detail tmp = (Detail)o;
                f.set(tmp, producerId);
                someStorage.putDetail(tmp);
            }
            Thread.sleep((long) (1000 / avgSpeed));
            //System.out.println("Producer TEST:" + someDetail.getType() + someDetail.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        putDetail();
    }

    @Override
    public void update(double value) {
        setSpeed(value);
    }

    public void setSpeed(double speed){
        avgSpeed = speed;
    }

    public String getSpeed(){
        return Double.toString(avgSpeed);
    }

}
