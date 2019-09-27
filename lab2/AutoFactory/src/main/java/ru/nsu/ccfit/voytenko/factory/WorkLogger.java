package ru.nsu.ccfit.voytenko.factory;

//import java.util.*;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.*;

public class WorkLogger {
    private static final Logger LOGGER = Logger.getLogger(WorkLogger.class.getName());
    private static FileHandler fileHandler = null;

    public static void init(){
        try{
            fileHandler = new FileHandler("factoryLog.log", false);
        }catch(IOException e){
            e.printStackTrace();
        }

        Logger logger = Logger.getLogger("");
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        logger.setLevel(Level.CONFIG);
    }
}
