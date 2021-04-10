package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class Main2 extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent client = FXMLLoader.load(getClass().getResource("../resources/server.fxml"));
        primaryStage.setTitle("SimpleBBS Server v1.0");
        primaryStage.setScene(new Scene(client, 600, 600));
        primaryStage.show();

    }

    @FXML
    TextArea textArea;

    @FXML
    Button exitButton;

    @FXML
    public void press(ActionEvent event) throws IOException {
        System.exit(0);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
