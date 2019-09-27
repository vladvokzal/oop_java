package ru.nsu.ccfit.voytenko.factory;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Factory {
    private StorageController controller;

    public void execute(ConfigInfo config) throws InterruptedException {
        WorkLogger.init();
        fillSizes(config);
        motorStorage = new Storage(this.motorStorageName, mSize);
        bodyStorage = new Storage(this.bodyStorageName, bSize);
        accessoryStorage = new Storage(this.accessoryStorageName, acSize);
        autoStorage = new Storage(this.autoStorageName, auSize);
        controller = new StorageController(bodyStorage, accessoryStorage, motorStorage, autoStorage, threadsCont);

        initThreads();

    }

    public void initThreads() throws InterruptedException {
        LinkedList<Thread> threads = new LinkedList<>();

        Producer motorProducer = new Producer(motorStorage, "Motor", aloneProducer, 0);
        ViewController.instance().addSliderMotorSubscriber(motorProducer);
        Thread motorProdThread = new Thread(motorProducer);

        Producer bodyProducer = new Producer(bodyStorage, "Body", aloneProducer, 0);
        ViewController.instance().addSliderBodySubscriber(bodyProducer);
        Thread bodyProdThread = new Thread(bodyProducer);

        threads.add(motorProdThread);
        threads.add(bodyProdThread);

        for (int i = 0; i < suppliersCount; i++){
            Producer accessoryProducer = new Producer(accessoryStorage, "Accesories", notAloneProducer, i);
            ViewController.instance().addSliderAccessorySubscriber(accessoryProducer);
            threads.add(new Thread(accessoryProducer));
        }
        threads.add(new Thread(controller));

        for (int i = 0; i < dealersCount; i++){
            threads.add(new Thread(new Dealer(autoStorage)));
        }

        for(Thread t : threads){
            t.start();
        }
        //System.out.println("NUM : " + Thread.activeCount());

    }


    public void fillSizes(ConfigInfo config){
        mSize = config.getSize("StorageMotorSize");
        bSize = config.getSize("StorageBodySize");
        acSize = config.getSize("StorageAccessorySize");
        auSize = config.getSize("StorageAutoSize");
        suppliersCount = config.getSize("AccessorySuppliers");
        threadsCont = config.getSize("Workers");
        dealersCount = config.getSize("Dealers");
    }

    public String getCurrentMotorsCount(){
        return Integer.toString(motorStorage.getStorageSize());
    }

    public String getCurrentBodysCount(){
        return Integer.toString(bodyStorage.getStorageSize());
    }

    public String getCurrentAccessorysCount(){
        return Integer.toString(accessoryStorage.getStorageSize());
    }

    private final static Logger logger = Logger.getLogger(WorkLogger.class.getName());

    private Storage motorStorage;
    private String motorStorageName = "MotorStorage";
    private boolean aloneProducer = true;
    private int mSize;

    private Storage bodyStorage;
    private String bodyStorageName = "BodyStorage";
    private int bSize;

    private Storage accessoryStorage;
    private String accessoryStorageName = "AccessoryStorage";
    private boolean notAloneProducer = false;
    private int acSize;

    private Storage autoStorage;
    private String autoStorageName = "AutoStorage";
    private int auSize;

    private int suppliersCount;
    private int threadsCont;
    private int dealersCount;
}
