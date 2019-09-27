package ru.nsu.ccfit.voytenko.factory;

public class Accesories implements Detail {

    private int id;
    private String type;

    public Accesories() {
        this.type = "Accessory";
    }

    public String getType(){
        return type;
    }

    public int getId() {
        return id;
    }
}
