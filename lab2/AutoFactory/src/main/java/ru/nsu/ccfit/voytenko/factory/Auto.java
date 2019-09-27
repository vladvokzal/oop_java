package ru.nsu.ccfit.voytenko.factory;

public class Auto implements Detail {

    private int id;
    private String type;
    Detail m;
    Detail ac;
    Detail b;

    public Auto(int id, Detail motor, Detail accesories, Detail body){
        this.m = motor;
        this.ac = accesories;
        this.b = body;
        this.type = "Auto";
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }
}
