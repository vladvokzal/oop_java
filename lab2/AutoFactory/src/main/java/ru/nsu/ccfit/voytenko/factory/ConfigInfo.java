package ru.nsu.ccfit.voytenko.factory;

import java.lang.*;
import java.io.*;
import java.util.*;

public class ConfigInfo {

    //store pairs of StoregeBodySize, Workers, etc... and it's values
    private HashMap<String, Integer> infoMap = new HashMap<>();

    private boolean isLogFileEnable;

    void parse(String configFile){

        String currentName;
        String currentValue;
        int readedSize;

        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))){
            String line = reader.readLine();

            while(line != null){
                String[] parts = line.split("=");
                currentName = parts[0];
                currentValue = parts[1];

                if(currentName == "LogSale"){
                    isLogFileEnable = Boolean.parseBoolean(currentValue);
                }

                readedSize = Integer.parseInt(currentValue);
                infoMap.put(currentName, readedSize);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    public int getSize(String name){
        return infoMap.get(name);
    }

    public boolean getLogState(){
        return  isLogFileEnable;
    }
}
