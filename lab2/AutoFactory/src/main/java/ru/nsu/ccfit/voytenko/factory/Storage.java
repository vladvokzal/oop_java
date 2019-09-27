package ru.nsu.ccfit.voytenko.factory;

import java.util.LinkedList;
import java.util.Queue;

public class Storage {

    private String storageType;
    private int storageCapacity;
    private int currentDetailCount;
    private Queue<Detail> storage;
    private static final Object obj = new Object();

    Storage(String type, int storageSize){
        this.storageCapacity = storageSize;
        storage = new LinkedList<>();
        this.storageType = type;
    }

    public Detail getDetail() throws InterruptedException {
        synchronized (obj){
            while(storage.isEmpty()){
                obj.wait();
            }
            Detail d;
            d = storage.remove();
            currentDetailCount--;
            obj.notify();
            return d;
        }
    }

    public void putDetail(Detail d) throws InterruptedException {
        synchronized (obj){
            while(getStorageSize() >= storageCapacity){
                obj.wait();
            }
            storage.add(d);
            currentDetailCount++;
            obj.notify();
        }

    }

    public synchronized boolean isEmpty(){
        return storage.isEmpty();
    }

    public synchronized int getStorageSize(){
        return currentDetailCount;
    }

    public synchronized int getStorageCapacity(){
        return storageCapacity;
    }


}
