package ru.nsu.ccfit.voytenko.factory;

public class Body implements Detail {

    private int id;
    private String type;

    public Body() {
        this.type = "Body";
    }

    public String getType(){
        return type;
    }

    public int getId() {
        return id;
    }
}
