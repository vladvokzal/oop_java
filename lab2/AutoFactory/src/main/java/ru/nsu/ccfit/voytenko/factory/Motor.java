package ru.nsu.ccfit.voytenko.factory;

public class Motor implements Detail {

    private int id;
    private String type;

    public Motor(){
        this.type = "Motor";
    }

    public String getType(){
        return type;
    }

    public int getId() {
        return id;
    }

}
