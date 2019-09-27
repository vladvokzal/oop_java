package ru.nsu.ccfit.voytenko.factory;

import javafx.fxml.*;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewController = this;
    }

    public static ViewController instance(){
        if(null == viewController){
            viewController = new ViewController();
        }
        return viewController;
    }

    /* Connect Dealer Slider with it's subscribers, send value to label */

    @FXML private Slider sliderDealer;
    private List<Subscriber> subscribersDragSliderDealer = new ArrayList<>();
    public void dragSliderDealer(MouseEvent mouseEvent){
        subscribersDragSliderDealer.forEach(x -> x.update(sliderDealer.getValue()));
    }

    synchronized public void addSliderDealerSubscriber(Subscriber subscriber){
        subscribersDragSliderDealer.add(subscriber);
    }

    @FXML private Label dealerLabel = new Label(Double.toString(sliderDealer.getValue()));

    /* Connect Motor Slider with it's subscribers, send value to label */

    @FXML private Slider sliderMotor;
    private List<Subscriber> subscribersDragSliderMotor = new ArrayList<>();
    public void dragSliderMotor(MouseEvent mouseEvent){
        subscribersDragSliderMotor.forEach(x -> x.update(sliderMotor.getValue()));
    }

    synchronized public void addSliderMotorSubscriber(Subscriber subscriber){
        subscribersDragSliderMotor.add(subscriber);
    }

    @FXML private Label motorProducerLabel = new Label(Double.toString(sliderMotor.getValue()));


    /* Connect Body Slider with it's subscribers, send value to label */

    @FXML private Slider sliderBody;
    private List<Subscriber> subscribersDragSliderBody = new ArrayList<>();
    public void dragSliderBody(MouseEvent mouseEvent){
        subscribersDragSliderBody.forEach(x -> x.update(sliderBody.getValue()));
    }

    synchronized public void addSliderBodySubscriber(Subscriber subscriber){
        subscribersDragSliderBody.add(subscriber);
    }

    @FXML private Label bodyProducerLabel = new Label(Double.toString(sliderBody.getValue()));


    /* Connect Accessory Slider with it's subscribers, send value to label */

    @FXML private Slider sliderAccessory;
    private List<Subscriber> subscribersDragSliderAccessory = new ArrayList<>();
    public void dragSliderAccessory(MouseEvent mouseEvent){
        subscribersDragSliderAccessory.forEach(x -> x.update(sliderAccessory.getValue()));
    }


    synchronized public void addSliderAccessorySubscriber(Subscriber subscriber){
        subscribersDragSliderAccessory.add(subscriber);
    }

    @FXML private Label accessoryProducerLabel = new Label(Double.toString(sliderAccessory.getValue()));

    @FXML private Label motorCountLabel;
    public void updateMotorStorage(){
        while (true){
            motorCountLabel.setText(Controller.instance().motorsOnStorage());
        }
    }

    @FXML private Label bodyCountLabel;
    public void updateBodyStorage(){
        while (true){
            bodyCountLabel.setText(Controller.instance().bodyOnStorage());
        }
    }

    @FXML private Label accessoryCountLabel;
    public void updateAccessoryStorage(){
        while (true){
            accessoryCountLabel.setText(Controller.instance().accessoryOnStorage());
        }
    }

    private ViewController() {}

    private static ViewController viewController = null;
}
