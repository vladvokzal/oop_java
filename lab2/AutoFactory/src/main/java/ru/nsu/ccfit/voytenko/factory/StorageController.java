package ru.nsu.ccfit.voytenko.factory;

import ru.nsu.ccfit.voytenko.threadpool.ThreadPool;

public class StorageController implements Runnable {

    private Storage bodyStorage;
    private Storage accessoryStorage;
    private Storage motorStorage;
    private Storage autoStorage;
    ThreadPool threadPool;

    StorageController(Storage bodyStorage,
                      Storage accessoryStorage, Storage motorStorage, Storage autoStorage, int tCount){
        this.accessoryStorage = accessoryStorage;
        this.bodyStorage = bodyStorage;
        this.motorStorage = motorStorage;
        this.autoStorage = autoStorage;
        threadPool = new ThreadPool(tCount);
    }

    public void createTask() throws InterruptedException {
        int iterations = autoStorage.getStorageCapacity();
        for (int i = 0; i < iterations; i++){
            Detail motor = motorStorage.getDetail();
            Detail body =  bodyStorage.getDetail();
            Detail accesories = accessoryStorage.getDetail();
            
	    WorkerTask task = new WorkerTask(motor, body, accesories, autoStorage);
            System.out.println("New task was created.");
            threadPool.addTask(task);
            System.out.println("Task was added to ThreadPool");
        }
    }

    @Override
    public void run() {
        try {
            createTask();
            threadPool.exec();
        } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
