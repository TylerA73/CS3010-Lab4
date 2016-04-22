/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;
/**

 *
 * @author Tyler Arseneault
 */

class WavePane extends Pane {
    private Polyline curve;
    
    double xScale = this.getWidth()/6.25;
    double yScale = this.getHeight()/10;
    
    private SimpleDoubleProperty a = new SimpleDoubleProperty();
    private SimpleDoubleProperty b = new SimpleDoubleProperty();
    private SimpleDoubleProperty c = new SimpleDoubleProperty();
    private SimpleDoubleProperty d = new SimpleDoubleProperty();
    
    private Line xAxis = new Line();
    private Line yAxis = new Line();
    
    private Text maxY = new Text("5");
    private Text maxX = new Text("\u03c0");
    private Text minY = new Text("-5");
    private Text minX = new Text("-" + "\u03c0");
    
    public WavePane(double a, double b, double c, double d){
        this.a.set(a);
        this.b.set(b);
        this.c.set(c);
        this.d.set(d);
        
        setAxis();
        setCurve();
        
    }
    
    public WavePane(){
        a.set(1.0);
        b.set(1.0);
        c.set(0.0);
        d.set(0.0);
        
        setAxis();
        setCurve();
        
    }
    
    public void setAxis(){
        
        xAxis.setStroke(Color.BLACK);
        yAxis.setStroke(Color.BLACK);
        xAxis.setStrokeWidth(2.0);
        yAxis.setStrokeWidth(2.0);
        
        xAxis.setStartX(0.0);
        xAxis.setEndX(this.getWidth());
        xAxis.setStartY(this.getHeight()/2);
        xAxis.setEndY(this.getHeight()/2);
            
        yAxis.setStartX(this.getWidth()/2);
        yAxis.setEndX(this.getWidth()/2);
        yAxis.setStartY(0.0);
        yAxis.setEndY(this.getHeight());
        
        this.getChildren().addAll(xAxis, yAxis, minX, maxX, minY, maxY);
        
        this.widthProperty().addListener(e -> {
            xAxis.setStartX(0.0);
            xAxis.setEndX(this.getWidth());
            xAxis.setStartY(this.getHeight()/2);
            xAxis.setEndY(this.getHeight()/2);
            
            yAxis.setStartX(this.getWidth()/2);
            yAxis.setEndX(this.getWidth()/2);
            yAxis.setStartY(0.0);
            yAxis.setEndY(this.getHeight());
            
            minX.setX(xAxis.getStartX());
            minX.setY(xAxis.getStartY() + 15);
            
            maxX.setX(xAxis.getEndX() - 15);
            maxX.setY(xAxis.getEndY() + 15);
            
            minY.setX(yAxis.getStartX() - 15);
            minY.setY(yAxis.getEndY() - 15);
            
            maxY.setX(yAxis.getEndX() - 15);
            maxY.setY(yAxis.getStartY() + 15);
            
            xScale = this.getWidth()/6.25;
            drawCurve();
            
            
        });
        
        this.heightProperty().addListener(e -> {
            xAxis.setStartX(0.0);
            xAxis.setEndX(this.getWidth());
            xAxis.setStartY(this.getHeight()/2);
            xAxis.setEndY(this.getHeight()/2);
            
            yAxis.setStartX(this.getWidth()/2);
            yAxis.setEndX(this.getWidth()/2);
            yAxis.setStartY(0.0);
            yAxis.setEndY(this.getHeight());
            
            minX.setX(xAxis.getStartX());
            minX.setY(xAxis.getStartY() + 15);
            
            maxX.setX(xAxis.getEndX() - 15);
            maxX.setY(xAxis.getEndY() + 15);
            
            minY.setX(yAxis.getStartX() - 15);
            minY.setY(yAxis.getEndY() - 15);
            
            maxY.setX(yAxis.getEndX() - 15);
            maxY.setY(yAxis.getStartY() + 15);
            
            yScale = this.getHeight()/10;
            drawCurve();
        });
        
        a.addListener(e -> {
            drawCurve();
        });
        
        b.addListener(e -> {
            drawCurve();
        });
        
        c.addListener(e -> {
            drawCurve();
        });
        
        d.addListener(e -> {
            drawCurve();
        });
        
    }
    
    public void setCurve(){
        double x = 0;
        double y = 0;
        
        curve = new Polyline();
        
        curve.setStroke(Color.BLACK);
        curve.setStrokeWidth(1.0);
        
        curve.setTranslateY(this.getHeight()/2);
        
        while(x < 6.28){
            y = calculateY(x);
            curve.getPoints().addAll(x*xScale, y*yScale);
            x += 0.01;
        }
        this.getChildren().add(curve);
    }
    
    public void reset(){
        this.getChildren().remove(curve);
        
        a.set(1.0);
        b.set(1.0);
        c.set(0.0);
        d.set(0.0);
        
        curve = new Polyline();
        this.setCurve();
        
        
    }
    
    public void drawCurve(){
        this.getChildren().remove(curve);
        setCurve();
        
        
    }
    
    public void setA(double a){
        this.a.set(a);
        
    }
    
    public void setB(double b){
        this.b.set(b);
        
    }
    
    public void setC(double c){
        this.c.set(c);
        
    }
    
    public void setD(double d){
        this.d.set(-d);
        
    }
    
    public SimpleDoubleProperty getA(){
       return a;
       
    }
    
    public SimpleDoubleProperty getB(){
        return b;
        
    }
    
    public SimpleDoubleProperty getC(){
        return c;
        
    }
    
    public SimpleDoubleProperty getD(){
        return d;
        
    }
    
    public double calculateY(double x){
       return (a.getValue()*Math.sin((b.getValue()*x) + c.getValue()) + d.getValue()); 
        
    }
    
}

public class Lab4 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        final double PI = 3.14159; 
        final int ZERO = 0;
        final int FIVE = 5;
        final int TEN = 10;
        
        WavePane wave = new WavePane();
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(wave);
        wave.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        
        HBox bottom = new HBox();
        
        Button reset = new Button("Reset");
        Button animate = new Button("Animate");
        
        TextField inputA, inputB, inputC, inputD;
        inputA = new TextField();
        inputB = new TextField();
        inputC = new TextField();
        inputD = new TextField();
        
        inputA.setPrefColumnCount(2);
        inputB.setPrefColumnCount(2);
        inputC.setPrefColumnCount(2);
        inputD.setPrefColumnCount(2);
        
        Text a, b, c, d;
        a = new Text("  A: ");
        b = new Text("  B: ");
        c = new Text("  C: ");
        d = new Text("  D: ");
        
        inputA.setOnAction( e -> {
            try{
                if((Double.parseDouble(inputA.getText()) > FIVE) ||
                  Double.parseDouble(inputA.getText()) < ZERO)
                  System.out.println("Number out of  bounds. Must be between 0 and 5");
                else {
                    wave.setA(Double.parseDouble(inputA.getText()));
                    wave.drawCurve();
                }
                
            }
            catch(NumberFormatException r){
               System.out.println("NOT A VALID NUMBER");
            }
            
            inputA.clear();
        
        });
        
        inputB.setOnAction( e -> {
            try{
                if((Double.parseDouble(inputB.getText()) > TEN) ||
                   (Double.parseDouble(inputB.getText()) < ZERO))
                    System.out.println("Number out of bounds. Must be between 0 and 10");
                else {
                    wave.setB(Double.parseDouble(inputB.getText()));
                    wave.drawCurve();
                }
            }
            catch(Exception r){
                System.out.println("NOT A VALID NUMBER");
            }
            inputB.clear();
        });
        
        inputC.setOnAction( e -> {
            try{
                if((Double.parseDouble(inputC.getText()) > PI) ||
                    (Double.parseDouble(inputC.getText()) < (-1)*PI))
                    System.out.println("Number out of bounds. Must be between -PI and PI");
                else {
                    wave.setC(Double.parseDouble(inputC.getText()));
                    wave.drawCurve();
                }
            }catch (Exception r){
                System.out.println("NOT A VALID NUMBER");
            }
            inputC.clear();
        });
        
        inputD.setOnAction( e -> {
            try{
                if((Double.parseDouble(inputD.getText()) > FIVE) ||
                   (Double.parseDouble(inputD.getText()) < (-1)*FIVE))
                    System.out.println("Number out of bounds. Must be between -5 and 5.");
                else {
                    wave.setD(Double.parseDouble(inputD.getText()));
                    wave.drawCurve();
                }
            }catch(Exception r){
                System.out.println("NOT A VALID NUMBER");
            }
            inputD.clear();
        });
        
        reset.setOnAction(e -> {
            wave.reset();
            
        });
        
        animate.setOnAction(e ->{
            EventHandler<ActionEvent> finish = f -> {
                wave.setA(1.0);
                wave.setB(1.0);
                wave.setC(0.0);
                wave.setD(0.0);
                
                inputA.setDisable(false);
                inputB.setDisable(false);
                inputC.setDisable(false);
                inputD.setDisable(false);
                
                reset.setDisable(false);
                animate.setDisable(false);
                
            };
            
            wave.setA(1.0);
            wave.setB(1.0);
            wave.setC(0.0);
            wave.setD(0.0);
            
            inputA.setDisable(true);
            inputB.setDisable(true);
            inputC.setDisable(true);
            inputD.setDisable(true);
            
            reset.setDisable(true);
            animate.setDisable(true);
            
            Timeline timeline = new Timeline();
            timeline.setCycleCount(0);
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000),
                                        new KeyValue(wave.getA(), 5)));
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000),
                                        new KeyValue(wave.getB(), 10)));
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000),
                                        finish));
            timeline.play();
            
        });
        
        bottom.getChildren().addAll(a, inputA, b, inputB, c, inputC, d, inputD, animate, reset);
        
        borderPane.setBottom(bottom);
        
        
        
        
        Scene scene = new Scene(borderPane, 1000, 800);
        borderPane.setPrefSize(scene.getWidth(), scene.getHeight());
        wave.setPrefSize(scene.getWidth(), scene.getHeight());
        
        wave.widthProperty().addListener(e -> {
            wave.setPrefWidth(scene.getWidth());
            
        });
        
        wave.heightProperty().addListener(e -> {
            wave.setPrefHeight(scene.getHeight());
            
        });
        
        primaryStage.setTitle("Wave");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("sine.jpg")));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
