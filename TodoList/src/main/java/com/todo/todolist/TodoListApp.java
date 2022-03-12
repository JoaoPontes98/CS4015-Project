package com.todo.todolist;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;

public class TodoListApp extends Application {
    int windowHeight = 500;
    int windowLength = 320;

    @Override
    public void start(Stage stage) throws IOException {
        // Splits the scene into 5 regions (top, bottom, center, left, right)
        BorderPane border = new BorderPane();

        // Make the header and footer and setting their regions
        HBox header = new HBox();
        VBox list = new VBox();
        HBox footer = new HBox();
        border.setTop(header);
        border.setCenter(list);
        border.setBottom(footer);

        // Setting the padding
        header.setPadding(new Insets(15, 12, 15, 12));
        header.setSpacing(10);
        header.setAlignment(Pos.CENTER);

        list.setPadding(new Insets(10));
        list.setSpacing(8);

        footer.setPadding(new Insets(15, 12, 15, 12));
        footer.setSpacing(10);
        footer.setAlignment(Pos.CENTER);

        // Header Components
        Button prevBtn = new Button("<");
        Text dateDisplay = new Text();
        Button nextBtn = new Button(">");
        dateDisplay.setText("02/05/2022");
        header.getChildren().addAll(prevBtn, dateDisplay, nextBtn);

        // List Components
        Text todo1 = new Text("[ ] Shovel Driveway");
        todo1.setFont(Font.font("Arial", 14));
        Text todo2 = new Text("[ ] Feed Cat");
        todo2.setFont(Font.font("Arial", 14));
        list.getChildren().addAll(todo1, todo2);

        Text subList[] = new Text[]{
                new Text("Groceries"), // list title
                new Text("[ ] Bread"),
                new Text("[ ] Milk"),
                new Text("[ ] Pizza"),
                new Text("+")
        };

        for (int i=0; i< subList.length; i++) {
            if(i == 0){ // title formatting
                subList[i].setFont(Font.font("Arial", FontWeight.BOLD, 14));
            }else{
                VBox.setMargin(subList[i], new Insets(0, 0, 0, 8));
            }
            list.getChildren().add(subList[i]);
        }

        Text newTask = new Text("+");
        todo2.setFont(Font.font("Arial", 14));
        list.getChildren().addAll(newTask);

        // Footer Components
        Button browseBtn = new Button("Browse");
        Button createBtn = new Button("Create New List");
        footer.getChildren().addAll(browseBtn, createBtn);

        // Setting the scene
        Scene scene = new Scene(border, windowLength, windowHeight);
        stage.setTitle("Todo List");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}