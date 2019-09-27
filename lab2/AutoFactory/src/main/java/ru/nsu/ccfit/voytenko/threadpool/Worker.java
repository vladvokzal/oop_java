package ru.nsu.ccfit.voytenko.threadpool;

import ru.nsu.ccfit.voytenko.factory.*;

public class Worker{

    Runnable wt;

    public void acceptTask(Runnable workerTask){
        wt = workerTask;
    }

    public void doJob() {
            wt.run();
    }
}