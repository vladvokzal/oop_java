package ru.nsu.ccfit.voytenko.factory;

import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;


public class View extends Application {

    private final Color textColor = Color.BLACK;
    private Label motorCountValue;

    public void launchApp(String[] args){
        launch(args);
    }

    public void repaintMotorCount(String newValue){
        motorCountValue.setText("WTF");
    }

    @Override
    public void start(Stage myStage){

        motorCountValue = new Label();

        myStage.setTitle("AUTO FACTORY");
        FlowPane rootNode = new FlowPane(Orientation.VERTICAL);
        rootNode.setColumnHalignment(HPos.LEFT);
        rootNode.setPrefWrapLength(500);
        rootNode.setAlignment(Pos.TOP_CENTER);

        Scene myScene = new Scene(rootNode, 850, 550, Color.LIGHTGREY);
        myStage.setScene(myScene);

        //Crating and adding main title
        HBox mainTitleLayout = new HBox(5);
        Label mainTitleLabel = new Label(" AUTO FACTORY ");
        mainTitleLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 45));
        mainTitleLayout.setPadding(new Insets(10, 0, 0, 0));
        mainTitleLayout.setAlignment(Pos.TOP_CENTER);
        mainTitleLayout.getChildren().add(mainTitleLabel);
        rootNode.getChildren().add(mainTitleLayout);
        //*******************SECTION END************************


        //Creating and adding speed title
        HBox speedTitleLayout = new HBox(5);
        Label speedTitleLabel = new Label(" SPEED ");
        speedTitleLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        speedTitleLayout.setPadding(new Insets(35, 0, 20, 0));
        speedTitleLayout.setAlignment(Pos.TOP_CENTER);
        speedTitleLayout.getChildren().add(speedTitleLabel);
        rootNode.getChildren().add(speedTitleLayout);
        //********************SECTION END***************************


        //Creating and adding speed data - sliders's name, sliders, values on it
        HBox speedDataLayout = new HBox(20);
        speedDataLayout.setPadding(new Insets(10, 30, 20, 50));
        Slider dealerSpeed = new Slider(1, 100, 20);
        Slider producerSpeed = new Slider(1, 100, 20);
        Label dealerCaption = new Label("Dealer speed: ");
        Label producerCaption = new Label("Producer speed: ");
        Label dealerSpeedValue = new Label(Double.toString(dealerSpeed.getValue()));
        Label producerSpeedValue = new Label(Double.toString(dealerSpeed.getValue()));
        speedDataLayout.setAlignment(Pos.TOP_LEFT);
        dealerCaption.setTextFill(textColor);
        producerCaption.setTextFill(textColor);
        dealerSpeedValue.setTextFill(textColor);
        producerSpeedValue.setTextFill(textColor);
        dealerSpeed.setShowTickLabels(true);
        dealerSpeed.setShowTickMarks(true);
        dealerSpeed.setBlockIncrement(2.5);
        producerSpeed.setShowTickMarks(true);
        producerSpeed.setShowTickLabels(true);
        producerSpeed.setBlockIncrement(2.5);


        dealerSpeed.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                                Number oldValue, Number newValue) {
                dealerSpeedValue.setText(String.format("%.2f", newValue));
            }
        });

        producerSpeed.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                                Number oldValue, Number newValue) {
                producerSpeedValue.setText(String.format("%.2f", newValue));
            }
        });


        speedDataLayout.getChildren().addAll(dealerCaption, dealerSpeed, dealerSpeedValue,
                producerCaption, producerSpeed, producerSpeedValue);

        rootNode.getChildren().add(speedDataLayout);
        //*********************SECTION END*************************

        //Creating and adding count data title
        HBox countTitleLayout = new HBox(5);
        Label countTitleLabel = new Label(" COUNT ");
        countTitleLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        countTitleLayout.setPadding(new Insets(35, 0, 20, 0));
        countTitleLayout.setAlignment(Pos.TOP_CENTER);
        countTitleLayout.getChildren().add(countTitleLabel);
        rootNode.getChildren().add(countTitleLayout);
        //************************SECTION END*********************************

        //Creating and adding detail's count on storages
        HBox countValuesLayout = new HBox(120);
        Label currentMotorCount = new Label("Motors: ");
        Label currentBodyCount = new Label("Bodys: ");
        Label currentAccessoryCount = new Label("Accessories: ");
        Label currentAutoCount = new Label("Autos: ");
        countValuesLayout.setPadding(new Insets(10, 30, 20, 50));
        countValuesLayout.setAlignment(Pos.TOP_LEFT);
        currentMotorCount.setTextFill(textColor);
        currentBodyCount.setTextFill(textColor);
        currentAccessoryCount.setTextFill(textColor);
        currentAutoCount.setTextFill(textColor);
        motorCountValue.setTextFill(textColor);

        countValuesLayout.getChildren().addAll(currentMotorCount, motorCountValue, currentBodyCount,
                currentAccessoryCount, currentAutoCount);
        rootNode.getChildren().add(countValuesLayout);
        //**********************SECTION END***********************************

        //myStage.showAndWait();
        myStage.show();

//        Label maxMotorCount = new Label();
//        Label maxBodyCount = new Label();
//        Label maxAccessoryCount = new Label();


//        class WorkingContext{
//
//            public void changeStates(){
//                currentMotorCount.setText(factory.getCurrentMotorsCount());
//                currentBodyCount.setText(factory.getCurrentBodysCount());
//                currentAccessoryCount.setText(factory.getCurrentAccessorysCount());
//            }
//        }
//
//        WorkingContext workingContext = new WorkingContext();
//        workingContext.changeStates();
//
//        while (true){
//            Platform.runLater(() -> {
//                currentAccessoryCount.setText(Integer.toString(i++));
//            });
//        }
    }

}