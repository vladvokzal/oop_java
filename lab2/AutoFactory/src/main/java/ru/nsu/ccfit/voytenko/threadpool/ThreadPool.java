package ru.nsu.ccfit.voytenko.threadpool;

import ru.nsu.ccfit.voytenko.factory.WorkerTask;

import java.util.*;

public class ThreadPool {
    private int tpSize;
    private Worker[] threadsNew;
    Queue<Runnable> tasksList;
    private static final Object obj = new Object();

    public ThreadPool(int poolSize){
        this.tpSize = poolSize;
        tasksList = new LinkedList<>();

        threadsNew = new Worker[tpSize];
        for (int i = 0; i < poolSize; i++){
            threadsNew[i] = new Worker();
        }
    }

    public Runnable getTask() throws InterruptedException {
        synchronized (obj){
            if (tasksList.isEmpty()){
                obj.wait();
            }
            Runnable task = tasksList.remove();
            obj.notify();
            return task;
        }
    }

    public void addTask(WorkerTask task){
        synchronized (obj){
            tasksList.add(task);
            obj.notify();
        }
    }

    public void exec() throws InterruptedException {
        for (Worker t : threadsNew){
            //System.out.println("In exec loop ...");
            Runnable wt = getTask();
            System.out.println("Task was taken from TP ");
            Worker worker = t;
            worker.acceptTask(wt);
            System.out.println("Tasks was accepted by worker");
            worker.doJob();
        }
    }

}
