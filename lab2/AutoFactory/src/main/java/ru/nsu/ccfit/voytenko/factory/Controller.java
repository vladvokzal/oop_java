package ru.nsu.ccfit.voytenko.factory;

public class Controller {

    static Controller controller = null;
    private Factory factory;

    private ConfigInfo configInfo;

    public static Controller instance(){
        if(null == controller){
            controller = new Controller();
        }
        return controller;
    }

    public void executeAll(){
        try {
            factory.execute(configInfo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String motorsOnStorage(){
        return factory.getCurrentMotorsCount();
    }

    public String bodyOnStorage(){
        return factory.getCurrentBodysCount();
    }

    public String accessoryOnStorage(){
        return factory.getCurrentAccessorysCount();
    }

    private Controller() {
        factory = new Factory();
        configInfo = new ConfigInfo();
        configInfo.parse("config.txt");
    }

}
