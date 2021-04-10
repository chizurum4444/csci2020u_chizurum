package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent client = FXMLLoader.load(getClass().getResource("../resources/client.fxml"));
        primaryStage.setTitle("SimpleBBS Client v1.0");
        primaryStage.setScene(new Scene(client, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
