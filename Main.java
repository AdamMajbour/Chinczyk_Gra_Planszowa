package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application
{
    Group root, root2;
    Map map;
    Pawn pawns;
    Button rzutKostka, nextScene;
    Label value, choose, welcome;
    Label[] statements;
    Action action;
    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException
    {
        map = new Map();
        pawns = new Pawn();

        value = new Label();
        value.setLayoutX(125);
        value.setLayoutY(235);
        value.setFont(Font.font("Arial", FontWeight.BOLD,70));

        choose = new Label("Wybierz pionek, którym chcesz się ruszyć");
        choose.setLayoutX(650);
        choose.setLayoutY(260);
        choose.setFont(Font.font("Arial", FontWeight.BOLD,17));
        choose.setVisible(false);

        statements = new Label[4];
        statements[0] = new Label("Żółty rzuca kostką");
        statements[1] = new Label("Zielony rzuca kostką");
        statements[2] = new Label("Czerwony rzuca kostką");
        statements[3] = new Label("Niebieski rzuca kostką");
        for (int i=0;i<4;i++)
        {
            statements[i].setLayoutX(650);
            statements[i].setLayoutY(220);
            statements[i].setFont(Font.font("Arial", 30));
            statements[i].setVisible(false);
        }

        rzutKostka = new Button("Rzuć kostką");
        rzutKostka.relocate(5,250);
        rzutKostka.setPrefSize(100,50);


        whichPawn();
        addToRoot();

        welcome = new Label("Kliknij 'Start' aby rozpocząć");
        welcome.setFont(Font.font("Arial",FontWeight.BOLD,30));
        welcome.setLayoutX(50);
        welcome.setLayoutY(150);
        welcome.setTextFill(Color.RED);
        nextScene = new Button("Start");
        nextScene.setLayoutX(200);
        nextScene.setLayoutY(250);
        nextScene.setPrefWidth(100);
        nextScene.setPrefHeight(20);
        nextScene.setOnAction(e->primaryStage.setScene(scene2));

        root2 = new Group(welcome, nextScene);

        scene1 = new Scene(root2,500,500,Color.BEIGE);
        scene2 = new Scene(root,1000, 1000, Color.BEIGE);

        action = new Action(rzutKostka, value, pawns, root, statements, choose);
        action.start();

        primaryStage.setTitle("Chińczyk - gra planszowa");
        primaryStage.setScene(scene1);
        primaryStage.setX(500);
        primaryStage.setY(5);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void addToRoot()
    {
        root = new Group(map.getFields());
        root.getChildren().addAll(pawns.getBluePawnView());
        root.getChildren().addAll(pawns.getYellowPawnView());
        root.getChildren().addAll(pawns.getGreenPawnView());
        root.getChildren().addAll(pawns.getRedPawnView());
        root.getChildren().addAll(rzutKostka);
        root.getChildren().addAll(value, choose);
        root.getChildren().addAll(statements);
    }

    public void whichPawn()
    {
        pawns.yellowPawnView[0].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            action.play(0);event.consume();});
        pawns.yellowPawnView[1].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            action.play(1);event.consume();});
        pawns.yellowPawnView[2].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            action.play(2);event.consume();});
        pawns.yellowPawnView[3].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            action.play(3);event.consume();});
        pawns.greenPawnView[0].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            action.play(0);event.consume();});
        pawns.greenPawnView[1].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            action.play(1);event.consume();});
        pawns.greenPawnView[2].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            action.play(2);event.consume();});
        pawns.greenPawnView[3].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            action.play(3);event.consume();});
        pawns.redPawnView[0].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            action.play(0);event.consume();});
        pawns.redPawnView[1].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            action.play(1);event.consume();});
        pawns.redPawnView[2].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            action.play(2);event.consume();});
        pawns.redPawnView[3].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            action.play(3);event.consume();});
        pawns.bluePawnView[0].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            action.play(0);event.consume();});
        pawns.bluePawnView[1].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            action.play(1);event.consume();});
        pawns.bluePawnView[2].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            action.play(2);event.consume();});
        pawns.bluePawnView[3].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            action.play(3);event.consume();});
    }
}